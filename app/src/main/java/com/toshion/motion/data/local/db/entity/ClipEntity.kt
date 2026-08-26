package com.toshion.motion.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clips")
data class ClipEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val projectId: Long,
    val mediaUri: String,
    val mediaMimeType: String?,
    val orderIndex: Int,
    val startTimeMs: Long,
    val sourceDurationMs: Long,
    val trimStartMs: Long,
    val trimEndMs: Long?
)
