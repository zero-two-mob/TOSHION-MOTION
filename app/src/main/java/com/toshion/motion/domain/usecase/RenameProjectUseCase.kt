package com.toshion.motion.domain.usecase

import com.toshion.motion.domain.repository.ProjectRepository
import javax.inject.Inject

class RenameProjectUseCase @Inject constructor(
    private val projectRepository: ProjectRepository
) {
    suspend operator fun invoke(projectId: Long, newName: String) {
        val project = projectRepository.getProject(projectId) ?: return
        projectRepository.updateProject(
            project.copy(name = newName, updatedAtEpochMillis = System.currentTimeMillis())
        )
    }
}
