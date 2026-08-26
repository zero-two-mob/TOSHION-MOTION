package com.toshion.motion.presentation.editor

import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackParameters
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.toshion.motion.domain.model.Clip
import com.toshion.motion.domain.model.Project
import com.toshion.motion.domain.repository.ProjectRepository
import com.toshion.motion.domain.usecase.AddClipUseCase
import com.toshion.motion.domain.usecase.DeleteClipUseCase
import com.toshion.motion.domain.usecase.GetClipsUseCase
import com.toshion.motion.domain.usecase.SplitClipUseCase
import com.toshion.motion.domain.usecase.TrimClipUseCase
import com.toshion.motion.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

enum class PreviewQuality(val label: String) {
    AUTO("Auto"),
    FULL("Full"),
    HALF("1/2"),
    QUARTER("1/4")
}

data class EditorUiState(
    val project: Project? = null,
    val isLoading: Boolean = true,
    val isPlaying: Boolean = false,
    val currentPositionMs: Long = 0L,
    val durationMs: Long = 0L,
    val playbackSpeed: Float = 1f,
    val isLooping: Boolean = false,
    val showSafeArea: Boolean = false,
    val showGrid: Boolean = false,
    val previewQuality: PreviewQuality = PreviewQuality.AUTO,
    val clips: List<Clip> = emptyList(),
    val selectedClipId: Long? = null,
    val timelineZoomPxPerSecond: Float = 60f
)

@HiltViewModel
class EditorViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val projectRepository: ProjectRepository,
    private val getClips: GetClipsUseCase,
    private val addClipUseCase: AddClipUseCase,
    private val trimClipUseCase: TrimClipUseCase,
    private val splitClipUseCase: SplitClipUseCase,
    private val deleteClipUseCase: DeleteClipUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    /** Exposed directly — VideoPreview wraps it in a PlayerView via AndroidView. */
    val player: ExoPlayer = ExoPlayer.Builder(context).build()

    private val _uiState = MutableStateFlow(EditorUiState())
    val uiState: StateFlow<EditorUiState> = _uiState.asStateFlow()

    private val projectId: Long = savedStateHandle.get<Long>(Screen.Editor.ARG_PROJECT_ID) ?: -1L

    init {
        player.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                _uiState.value = _uiState.value.copy(isPlaying = isPlaying)
            }
        })

        // Position doesn't change on discrete player "events" while actively
        // playing — this polls so the scrubber/time display actually moves
        // smoothly. Converts the player's per-clip-item position into a
        // single global timeline position using the clip list's precomputed
        // startTimeMs values (a multi-item ExoPlayer playlist resets
        // currentPosition to 0 at each item boundary, it does not report a
        // playlist-wide position on its own).
        viewModelScope.launch {
            while (true) {
                if (player.isPlaying) {
                    _uiState.value = _uiState.value.copy(currentPositionMs = globalPositionMs())
                }
                delay(200)
            }
        }

        viewModelScope.launch {
            val project = projectRepository.getProject(projectId)
            _uiState.value = _uiState.value.copy(project = project, isLoading = false)

            // Bridge Phase 2/3's single initialMediaUri into a real first
            // Clip, exactly once — everything after this reads from the
            // clips table, initialMediaUri is never referenced again.
            val initialClips = getClips(projectId).first()
            if (initialClips.isEmpty()) {
                project?.initialMediaUri?.let { uri ->
                    val mimeType = project.initialMediaMimeType
                    val durationMs = probeDurationMs(Uri.parse(uri), mimeType)
                    addClipUseCase(projectId, uri, mimeType, durationMs)
                }
            }

            getClips(projectId).collect { clipList ->
                _uiState.value = _uiState.value.copy(
                    clips = clipList,
                    durationMs = clipList.sumOf { it.durationMs }
                )
                rebuildPlayerPlaylist(clipList)
            }
        }
    }

    fun onPlayPause() {
        if (player.isPlaying) player.pause() else player.play()
    }

    fun onSeekTo(positionMs: Long) {
        seekToGlobal(positionMs)
    }

    /**
     * Skips a fixed 1 second. No forced pause, so it works during playback
     * too.
     */
    fun onSkip(forward: Boolean) {
        val target = uiState.value.currentPositionMs + if (forward) SKIP_STEP_MS else -SKIP_STEP_MS
        seekToGlobal(target)
    }

    fun onToggleLoop() {
        val newLooping = !_uiState.value.isLooping
        player.repeatMode = if (newLooping) Player.REPEAT_MODE_ALL else Player.REPEAT_MODE_OFF
        _uiState.value = _uiState.value.copy(isLooping = newLooping)
    }

    fun onSpeedChange(speed: Float) {
        player.playbackParameters = PlaybackParameters(speed)
        _uiState.value = _uiState.value.copy(playbackSpeed = speed)
    }

    fun onToggleSafeArea() {
        _uiState.value = _uiState.value.copy(showSafeArea = !_uiState.value.showSafeArea)
    }

    fun onToggleGrid() {
        _uiState.value = _uiState.value.copy(showGrid = !_uiState.value.showGrid)
    }

    fun onPreviewQualityChange(quality: PreviewQuality) {
        // Actually downscaling decode resolution is Phase 15 (Performance
        // Optimization) territory — this records the choice for real now.
        _uiState.value = _uiState.value.copy(previewQuality = quality)
    }

    fun onSelectClip(clipId: Long?) {
        _uiState.value = _uiState.value.copy(selectedClipId = clipId)
    }

    fun onZoomChange(pxPerSecond: Float) {
        _uiState.value = _uiState.value.copy(
            timelineZoomPxPerSecond = pxPerSecond.coerceIn(10f, 300f)
        )
    }

    /** Called once the timeline's own Photo Picker returns a result. */
    fun onAddClip(uriString: String, mimeType: String?) {
        viewModelScope.launch {
            val durationMs = probeDurationMs(Uri.parse(uriString), mimeType)
            addClipUseCase(projectId, uriString, mimeType, durationMs)
        }
    }

    fun onTrimSelectedClip(newTrimStartMs: Long, newTrimEndMs: Long?) {
        val clipId = uiState.value.selectedClipId ?: return
        viewModelScope.launch { trimClipUseCase(clipId, newTrimStartMs, newTrimEndMs) }
    }

    /** Splits whichever clip the playhead currently sits inside — a
     *  timeline-position operation, independent of manual selection. */
    fun onSplitAtPlayhead() {
        val position = uiState.value.currentPositionMs
        val clip = uiState.value.clips.firstOrNull { position in it.startTimeMs until it.endTimeMs }
            ?: return
        viewModelScope.launch { splitClipUseCase(clip, position) }
    }

    fun onDeleteSelectedClip() {
        val clipId = uiState.value.selectedClipId ?: return
        viewModelScope.launch {
            deleteClipUseCase(clipId)
            _uiState.value = _uiState.value.copy(selectedClipId = null)
        }
    }

    override fun onCleared() {
        player.release()
        super.onCleared()
    }

    private fun globalPositionMs(): Long {
        val clips = uiState.value.clips
        val itemIndex = player.currentMediaItemIndex
        val clipStart = clips.getOrNull(itemIndex)?.startTimeMs ?: 0L
        return clipStart + player.currentPosition.coerceAtLeast(0L)
    }

    /** Converts a global timeline position into (item index, local position)
     *  and seeks the player there — the inverse of [globalPositionMs]. */
    private fun seekToGlobal(globalPositionMs: Long) {
        val clips = uiState.value.clips
        if (clips.isEmpty()) return
        val totalMs = clips.sumOf { it.durationMs }
        val clamped = globalPositionMs.coerceIn(0L, totalMs)

        var accumulated = 0L
        var targetIndex = clips.lastIndex
        var localPositionMs = 0L
        for ((index, clip) in clips.withIndex()) {
            if (clamped < accumulated + clip.durationMs || index == clips.lastIndex) {
                targetIndex = index
                localPositionMs = (clamped - accumulated).coerceIn(0L, clip.durationMs)
                break
            }
            accumulated += clip.durationMs
        }

        player.seekTo(targetIndex, localPositionMs)
        _uiState.value = _uiState.value.copy(currentPositionMs = clamped)
    }

    private fun rebuildPlayerPlaylist(clips: List<Clip>) {
        if (clips.isEmpty()) return
        val mediaItems = clips.map { clip ->
            MediaItem.Builder()
                .setUri(clip.mediaUri)
                .setClippingConfiguration(
                    MediaItem.ClippingConfiguration.Builder()
                        .setStartPositionMs(clip.trimStartMs)
                        .setEndPositionMs(clip.effectiveTrimEndMs)
                        .build()
                )
                .build()
        }
        player.setMediaItems(mediaItems)
        player.prepare()
    }

    private suspend fun probeDurationMs(uri: Uri, mimeType: String?): Long {
        if (mimeType?.startsWith("image/") == true) return DEFAULT_IMAGE_DURATION_MS
        return withContext(Dispatchers.IO) {
            val retriever = MediaMetadataRetriever()
            try {
                retriever.setDataSource(context, uri)
                retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
                    ?.toLongOrNull()
                    ?.takeIf { it > 0 }
                    ?: DEFAULT_IMAGE_DURATION_MS
            } catch (e: Exception) {
                DEFAULT_IMAGE_DURATION_MS
            } finally {
                retriever.release()
            }
        }
    }

    private companion object {
        const val SKIP_STEP_MS = 1_000L
        const val DEFAULT_IMAGE_DURATION_MS = 3_000L
    }
}
