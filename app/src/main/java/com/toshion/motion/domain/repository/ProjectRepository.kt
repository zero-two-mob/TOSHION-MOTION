package com.toshion.motion.domain.repository

import com.toshion.motion.domain.model.Project
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {
    fun observeProjects(): Flow<List<Project>>
    suspend fun getProject(id: Long): Project?
    suspend fun createProject(project: Project): Long
    suspend fun updateProject(project: Project)
    suspend fun deleteProject(id: Long)
    suspend fun duplicateProject(id: Long): Long
}
