package com.toshion.motion.presentation.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toshion.motion.domain.model.AspectRatio
import com.toshion.motion.domain.model.ResolutionPreset
import com.toshion.motion.domain.model.resolutionFor
import com.toshion.motion.domain.usecase.CreateProjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CreateProjectUiState(
    val projectName: String = "",
    val aspectRatio: AspectRatio = AspectRatio.LANDSCAPE_16_9,
    val resolutionPreset: ResolutionPreset = ResolutionPreset.FHD_1080,
    val fps: Int = 30,
    val isCreating: Boolean = false
)

@HiltViewModel
class CreateProjectViewModel @Inject constructor(
    private val createProject: CreateProjectUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateProjectUiState())
    val uiState: StateFlow<CreateProjectUiState> = _uiState.asStateFlow()

    fun onNameChange(name: String) {
        _uiState.value = _uiState.value.copy(projectName = name)
    }

    fun onAspectRatioChange(ratio: AspectRatio) {
        _uiState.value = _uiState.value.copy(aspectRatio = ratio)
    }

    fun onResolutionPresetChange(preset: ResolutionPreset) {
        _uiState.value = _uiState.value.copy(resolutionPreset = preset)
    }

    fun onFpsChange(fps: Int) {
        _uiState.value = _uiState.value.copy(fps = fps)
    }

    /**
     * Called once the Photo Picker returns. A null [uriString] means the user
     * backed out without picking anything — per the spec, "import media" is
     * the completion step of Create Project, not an optional extra, so no
     * media means no project gets created.
     */
    fun onMediaPicked(uriString: String?, mimeType: String?, onCreated: (Long) -> Unit) {
        if (uriString == null) return
        val state = _uiState.value
        val name = state.projectName.ifBlank { "Untitled Project" }
        val (width, height) = resolutionFor(state.aspectRatio, state.resolutionPreset)

        _uiState.value = state.copy(isCreating = true)
        viewModelScope.launch {
            val id = createProject(
                name = name,
                resolutionWidth = width,
                resolutionHeight = height,
                fps = state.fps,
                initialMediaUri = uriString,
                initialMediaMimeType = mimeType
            )
            _uiState.value = CreateProjectUiState()
            onCreated(id)
        }
    }
}
