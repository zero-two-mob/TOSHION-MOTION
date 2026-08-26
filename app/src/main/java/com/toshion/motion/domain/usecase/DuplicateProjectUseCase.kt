package com.toshion.motion.domain.usecase

import com.toshion.motion.domain.repository.ProjectRepository
import javax.inject.Inject

class DuplicateProjectUseCase @Inject constructor(
    private val projectRepository: ProjectRepository
) {
    suspend operator fun invoke(projectId: Long): Long = projectRepository.duplicateProject(projectId)
}
