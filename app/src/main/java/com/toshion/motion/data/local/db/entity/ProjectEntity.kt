package com.toshion.motion.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val resolutionWidth: Int,
    val resolutionHeight: Int,
    val fps: Int,
    val createdAtEpochMillis: Long,
    val updatedAtEpochMillis: Long,
    val thumbnailPath: String?,
    val durationMs: Long,
    val initialMediaUri: String? = null,
    val initialMediaMimeType: String? = null
)
