package com.toshion.motion.domain.usecase

import com.toshion.motion.domain.model.Project
import com.toshion.motion.domain.repository.ProjectRepository
import javax.inject.Inject

class CreateProjectUseCase @Inject constructor(
    private val projectRepository: ProjectRepository
) {
    suspend operator fun invoke(
        name: String,
        resolutionWidth: Int,
        resolutionHeight: Int,
        fps: Int,
        initialMediaUri: String? = null,
        initialMediaMimeType: String? = null
    ): Long {
        val now = System.currentTimeMillis()
        return projectRepository.createProject(
            Project(
                name = name,
                resolutionWidth = resolutionWidth,
                resolutionHeight = resolutionHeight,
                fps = fps,
                createdAtEpochMillis = now,
                updatedAtEpochMillis = now,
                initialMediaUri = initialMediaUri,
                initialMediaMimeType = initialMediaMimeType
            )
        )
    }
}
