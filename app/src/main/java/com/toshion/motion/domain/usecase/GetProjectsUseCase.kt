package com.toshion.motion.domain.usecase

import com.toshion.motion.domain.model.Project
import com.toshion.motion.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProjectsUseCase @Inject constructor(
    private val projectRepository: ProjectRepository
) {
    operator fun invoke(): Flow<List<Project>> = projectRepository.observeProjects()
}
