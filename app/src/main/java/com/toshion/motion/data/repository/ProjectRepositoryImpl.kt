package com.toshion.motion.data.repository

import com.toshion.motion.data.local.db.dao.ProjectDao
import com.toshion.motion.data.local.db.entity.ProjectEntity
import com.toshion.motion.domain.model.Project
import com.toshion.motion.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(
    private val projectDao: ProjectDao
) : ProjectRepository {

    override fun observeProjects(): Flow<List<Project>> =
        projectDao.observeProjects().map { entities -> entities.map { it.toDomain() } }

    override suspend fun getProject(id: Long): Project? =
        projectDao.getProject(id)?.toDomain()

    override suspend fun createProject(project: Project): Long =
        projectDao.insert(project.toEntity())

    override suspend fun updateProject(project: Project) {
        projectDao.update(project.toEntity())
    }

    override suspend fun deleteProject(id: Long) {
        projectDao.delete(id)
    }

    override suspend fun duplicateProject(id: Long): Long {
        val original = projectDao.getProject(id) ?: return -1L
        val now = System.currentTimeMillis()
        val copy = original.copy(
            id = 0L,
            name = original.name + " copy",
            createdAtEpochMillis = now,
            updatedAtEpochMillis = now
        )
        return projectDao.insert(copy)
    }
}

private fun ProjectEntity.toDomain() = Project(
    id = id,
    name = name,
    resolutionWidth = resolutionWidth,
    resolutionHeight = resolutionHeight,
    fps = fps,
    createdAtEpochMillis = createdAtEpochMillis,
    updatedAtEpochMillis = updatedAtEpochMillis,
    thumbnailPath = thumbnailPath,
    durationMs = durationMs,
    initialMediaUri = initialMediaUri,
    initialMediaMimeType = initialMediaMimeType
)

private fun Project.toEntity() = ProjectEntity(
    id = id,
    name = name,
    resolutionWidth = resolutionWidth,
    resolutionHeight = resolutionHeight,
    fps = fps,
    createdAtEpochMillis = createdAtEpochMillis,
    updatedAtEpochMillis = updatedAtEpochMillis,
    thumbnailPath = thumbnailPath,
    durationMs = durationMs,
    initialMediaUri = initialMediaUri,
    initialMediaMimeType = initialMediaMimeType
)
