package com.toshion.motion.presentation.editor

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Loop
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Redo
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.toshion.motion.domain.model.Clip

private enum class BottomPanelTab(val label: String) {
    TIMELINE("Timeline"),
    LAYERS("Layers"),
    TOOLS("Tools"),
    PROPERTIES("Properties"),
    KEYFRAMES("Keyframes")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(
    onBack: () -> Unit,
    viewModel: EditorViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val addClipLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            val mimeType = context.contentResolver.getType(uri)
            viewModel.onAddClip(uri.toString(), mimeType)
        }
    }

    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    Scaffold(
        topBar = {
            EditorTopBar(projectName = uiState.project?.name ?: "Untitled", onBack = onBack)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                VideoPreview(
                    player = viewModel.player,
                    showSafeArea = uiState.showSafeArea,
                    showGrid = uiState.showGrid
                )
            }

            PlaybackControls(
                isPlaying = uiState.isPlaying,
                currentPositionMs = uiState.currentPositionMs,
                durationMs = uiState.durationMs,
                isLooping = uiState.isLooping,
                playbackSpeed = uiState.playbackSpeed,
                onPlayPause = viewModel::onPlayPause,
                onSeekTo = viewModel::onSeekTo,
                onSkip = viewModel::onSkip,
                onToggleLoop = viewModel::onToggleLoop,
                onSpeedChange = viewModel::onSpeedChange
            )

            PreviewOptionsRow(
                showSafeArea = uiState.showSafeArea,
                showGrid = uiState.showGrid,
                previewQuality = uiState.previewQuality,
                onToggleSafeArea = viewModel::onToggleSafeArea,
                onToggleGrid = viewModel::onToggleGrid,
                onPreviewQualityChange = viewModel::onPreviewQualityChange
            )

            EditorBottomPanel(
                clips = uiState.clips,
                selectedClipId = uiState.selectedClipId,
                currentPositionMs = uiState.currentPositionMs,
                zoomPxPerSecond = uiState.timelineZoomPxPerSecond,
                onSelectClip = viewModel::onSelectClip,
                onSeekTo = viewModel::onSeekTo,
                onZoomChange = viewModel::onZoomChange,
                onAddClip = {
                    addClipLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
                    )
                },
                onSplit = viewModel::onSplitAtPlayhead,
                onDeleteSelected = viewModel::onDeleteSelectedClip,
                onTrimSelected = viewModel::onTrimSelectedClip
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditorTopBar(projectName: String, onBack: () -> Unit) {
    TopAppBar(
        title = { Text(text = projectName, maxLines = 1) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            // Real widgets, correctly inert: there's no edit history to undo/
            // redo until Phase 4-7 give the timeline actual operations, no
            // Export Engine until Phase 14, no in-app settings destination
            // yet (Phase 2's Settings tab lives one level up, at Home), and
            // fullscreen toggling isn't wired up yet either. All five
            // disabled rather than silently doing nothing when tapped.
            IconButton(onClick = {}, enabled = false) {
                Icon(Icons.Default.Undo, contentDescription = "Undo")
            }
            IconButton(onClick = {}, enabled = false) {
                Icon(Icons.Default.Redo, contentDescription = "Redo")
            }
            TextButton(onClick = {}, enabled = false) {
                Text("Export")
            }
            IconButton(onClick = {}, enabled = false) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }
            IconButton(onClick = {}, enabled = false) {
                Icon(Icons.Default.Fullscreen, contentDescription = "Fullscreen")
            }
        }
    )
}

@Composable
private fun PlaybackControls(
    isPlaying: Boolean,
    currentPositionMs: Long,
    durationMs: Long,
    isLooping: Boolean,
    playbackSpeed: Float,
    onPlayPause: () -> Unit,
    onSeekTo: (Long) -> Unit,
    onSkip: (Boolean) -> Unit,
    onToggleLoop: () -> Unit,
    onSpeedChange: (Float) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Slider(
            value = if (durationMs > 0) currentPositionMs.toFloat() / durationMs else 0f,
            onValueChange = { fraction -> onSeekTo((fraction * durationMs).toLong()) },
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${formatTime(currentPositionMs)} / ${formatTime(durationMs)}",
                style = MaterialTheme.typography.labelMedium
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { onSkip(false) }) {
                    Icon(Icons.Default.SkipPrevious, contentDescription = "Back 1 second")
                }
                IconButton(onClick = onPlayPause) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Default.PauseCircle else Icons.Default.PlayCircle,
                        contentDescription = if (isPlaying) "Pause" else "Play",
                        modifier = Modifier.size(40.dp)
                    )
                }
                IconButton(onClick = { onSkip(true) }) {
                    Icon(Icons.Default.SkipNext, contentDescription = "Forward 1 second")
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                var speedMenuExpanded by remember { mutableStateOf(false) }
                Box {
                    TextButton(onClick = { speedMenuExpanded = true }) {
                        Text("${playbackSpeed}x")
                    }
                    DropdownMenu(
                        expanded = speedMenuExpanded,
                        onDismissRequest = { speedMenuExpanded = false }
                    ) {
                        listOf(0.25f, 0.5f, 1f, 1.5f, 2f, 4f).forEach { speed ->
                            DropdownMenuItem(
                                text = { Text("${speed}x") },
                                onClick = {
                                    onSpeedChange(speed)
                                    speedMenuExpanded = false
                                }
                            )
                        }
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(onClick = onToggleLoop) {
                        Icon(
                            imageVector = Icons.Default.Loop,
                            contentDescription = "Loop",
                            tint = if (isLooping) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    }
                    Text(
                        text = "Loop",
                        style = MaterialTheme.typography.labelSmall,
                        color = if (isLooping) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PreviewOptionsRow(
    showSafeArea: Boolean,
    showGrid: Boolean,
    previewQuality: PreviewQuality,
    onToggleSafeArea: () -> Unit,
    onToggleGrid: () -> Unit,
    onPreviewQualityChange: (PreviewQuality) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(selected = showSafeArea, onClick = onToggleSafeArea, label = { Text("Safe area") })
        FilterChip(selected = showGrid, onClick = onToggleGrid, label = { Text("Grid") })

        var qualityMenuExpanded by remember { mutableStateOf(false) }
        Box {
            FilterChip(
                selected = previewQuality != PreviewQuality.AUTO,
                onClick = { qualityMenuExpanded = true },
                label = { Text("Quality: ${previewQuality.label}") }
            )
            DropdownMenu(expanded = qualityMenuExpanded, onDismissRequest = { qualityMenuExpanded = false }) {
                PreviewQuality.entries.forEach { quality ->
                    DropdownMenuItem(
                        text = { Text(quality.label) },
                        onClick = {
                            onPreviewQualityChange(quality)
                            qualityMenuExpanded = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditorBottomPanel(
    clips: List<Clip>,
    selectedClipId: Long?,
    currentPositionMs: Long,
    zoomPxPerSecond: Float,
    onSelectClip: (Long?) -> Unit,
    onSeekTo: (Long) -> Unit,
    onZoomChange: (Float) -> Unit,
    onAddClip: () -> Unit,
    onSplit: () -> Unit,
    onDeleteSelected: () -> Unit,
    onTrimSelected: (Long, Long?) -> Unit
) {
    var selectedTab by remember { mutableStateOf(BottomPanelTab.TIMELINE) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
    ) {
        ScrollableTabRow(selectedTabIndex = selectedTab.ordinal, edgePadding = 8.dp) {
            BottomPanelTab.entries.forEach { tab ->
                Tab(
                    selected = selectedTab == tab,
                    onClick = { selectedTab = tab },
                    text = { Text(tab.label) }
                )
            }
        }

        if (selectedTab == BottomPanelTab.TIMELINE) {
            TimelineView(
                clips = clips,
                selectedClipId = selectedClipId,
                currentPositionMs = currentPositionMs,
                zoomPxPerSecond = zoomPxPerSecond,
                onSelectClip = onSelectClip,
                onSeekTo = onSeekTo,
                onZoomChange = onZoomChange,
                onAddClip = onAddClip,
                onSplit = onSplit,
                onDeleteSelected = onDeleteSelected,
                onTrimSelected = onTrimSelected,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = descriptionFor(selectedTab),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

private fun descriptionFor(tab: BottomPanelTab): String = when (tab) {
    BottomPanelTab.TIMELINE -> "The real timeline \u2014 unlimited layers, zoom, snapping, trim handles \u2014 is Phase 4."
    BottomPanelTab.LAYERS -> "Video/image/audio/text/shape/adjustment/mask layers arrive with Phase 5's Layer Engine."
    BottomPanelTab.TOOLS -> "Cut, trim, split, speed, crop, color, and the rest of the toolkit are Phase 6."
    BottomPanelTab.PROPERTIES -> "Shows the selected layer's properties once layers (Phase 5) exist to select."
    BottomPanelTab.KEYFRAMES -> "Keyframe animation with easing and motion paths is Phase 7."
}

private fun formatTime(ms: Long): String {
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}
