package com.toshion.motion.domain.usecase

import com.toshion.motion.domain.repository.ProjectRepository
import javax.inject.Inject

class DeleteProjectUseCase @Inject constructor(
    private val projectRepository: ProjectRepository
) {
    suspend operator fun invoke(projectId: Long) = projectRepository.deleteProject(projectId)
}
