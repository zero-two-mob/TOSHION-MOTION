package com.toshion.motion.presentation.projects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toshion.motion.domain.model.Project
import com.toshion.motion.domain.usecase.DeleteProjectUseCase
import com.toshion.motion.domain.usecase.DuplicateProjectUseCase
import com.toshion.motion.domain.usecase.GetProjectsUseCase
import com.toshion.motion.domain.usecase.RenameProjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ProjectSortOrder(val label: String) {
    LAST_EDITED("Last edited"),
    NAME("Name"),
    DATE_CREATED("Date created")
}

data class ProjectsUiState(
    val visibleProjects: List<Project> = emptyList(),
    val hasAnyProjects: Boolean = false,
    val searchQuery: String = "",
    val sortOrder: ProjectSortOrder = ProjectSortOrder.LAST_EDITED,
    val isLoading: Boolean = true
)

@HiltViewModel
class ProjectsViewModel @Inject constructor(
    private val getProjects: GetProjectsUseCase,
    private val deleteProject: DeleteProjectUseCase,
    private val duplicateProject: DuplicateProjectUseCase,
    private val renameProject: RenameProjectUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val _sortOrder = MutableStateFlow(ProjectSortOrder.LAST_EDITED)
    private val _allProjects = MutableStateFlow<List<Project>?>(null)

    val uiState: StateFlow<ProjectsUiState> = combine(
        _allProjects, _searchQuery, _sortOrder
    ) { projects, query, sort ->
        val loaded = projects ?: emptyList()
        val filtered = if (query.isBlank()) {
            loaded
        } else {
            loaded.filter { it.name.contains(query, ignoreCase = true) }
        }
        val sorted = when (sort) {
            ProjectSortOrder.LAST_EDITED -> filtered.sortedByDescending { it.updatedAtEpochMillis }
            ProjectSortOrder.NAME -> filtered.sortedBy { it.name.lowercase() }
            ProjectSortOrder.DATE_CREATED -> filtered.sortedByDescending { it.createdAtEpochMillis }
        }
        ProjectsUiState(
            visibleProjects = sorted,
            hasAnyProjects = loaded.isNotEmpty(),
            searchQuery = query,
            sortOrder = sort,
            isLoading = projects == null
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = ProjectsUiState()
    )

    init {
        viewModelScope.launch {
            getProjects().collect { projects -> _allProjects.value = projects }
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun onSortOrderChange(order: ProjectSortOrder) {
        _sortOrder.value = order
    }

    fun onDeleteProject(id: Long) {
        viewModelScope.launch { deleteProject(id) }
    }

    fun onDuplicateProject(id: Long) {
        viewModelScope.launch { duplicateProject(id) }
    }

    fun onRenameProject(id: Long, newName: String) {
        if (newName.isBlank()) return
        viewModelScope.launch { renameProject(id, newName.trim()) }
    }
}
