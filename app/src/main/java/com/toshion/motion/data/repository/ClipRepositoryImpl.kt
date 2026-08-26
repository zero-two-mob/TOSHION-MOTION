package com.toshion.motion.data.repository

import com.toshion.motion.data.local.db.dao.ClipDao
import com.toshion.motion.data.local.db.entity.ClipEntity
import com.toshion.motion.domain.model.Clip
import com.toshion.motion.domain.repository.ClipRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ClipRepositoryImpl @Inject constructor(
    private val clipDao: ClipDao
) : ClipRepository {

    override fun observeClips(projectId: Long): Flow<List<Clip>> =
        clipDao.observeClips(projectId).map { entities -> entities.map { it.toDomain() } }

    override suspend fun getClips(projectId: Long): List<Clip> =
        clipDao.getClips(projectId).map { it.toDomain() }

    override suspend fun addClip(
        projectId: Long,
        mediaUri: String,
        mediaMimeType: String?,
        sourceDurationMs: Long
    ): Long {
        val existing = clipDao.getClips(projectId)
        val nextOrderIndex = (existing.maxOfOrNull { it.orderIndex } ?: -1) + 1
        val startTimeMs = existing.sumOf { it.effectiveDurationMs() }
        return clipDao.insert(
            ClipEntity(
                projectId = projectId,
                mediaUri = mediaUri,
                mediaMimeType = mediaMimeType,
                orderIndex = nextOrderIndex,
                startTimeMs = startTimeMs,
                sourceDurationMs = sourceDurationMs,
                trimStartMs = 0L,
                trimEndMs = null
            )
        )
    }

    override suspend fun updateTrim(clipId: Long, trimStartMs: Long, trimEndMs: Long?) {
        val clip = clipDao.getClip(clipId) ?: return
        clipDao.update(clip.copy(trimStartMs = trimStartMs, trimEndMs = trimEndMs))
        repackClips(clip.projectId)
    }

    override suspend fun splitClip(clipId: Long, atSourcePositionMs: Long) {
        val clip = clipDao.getClip(clipId) ?: return
        val effectiveEnd = clip.trimEndMs ?: clip.sourceDurationMs
        if (atSourcePositionMs <= clip.trimStartMs || atSourcePositionMs >= effectiveEnd) return

        // First half reuses the original row — trimEnd just moves back.
        clipDao.update(clip.copy(trimEndMs = atSourcePositionMs))

        // Make room for the second half right after it, then insert it.
        clipDao.shiftOrderIndicesFrom(clip.projectId, clip.orderIndex + 1, by = 1)
        clipDao.insert(
            clip.copy(
                id = 0L,
                orderIndex = clip.orderIndex + 1,
                trimStartMs = atSourcePositionMs,
                trimEndMs = clip.trimEndMs
            )
        )
        repackClips(clip.projectId)
    }

    override suspend fun deleteClip(clipId: Long) {
        val clip = clipDao.getClip(clipId) ?: return
        clipDao.delete(clipId)
        clipDao.shiftOrderIndicesFrom(clip.projectId, clip.orderIndex + 1, by = -1)
        repackClips(clip.projectId)
    }

    /** Gapless timeline: recompute every clip's startTimeMs from current
     *  orderIndex + duration, after anything that could change either. */
    private suspend fun repackClips(projectId: Long) {
        val clips = clipDao.getClips(projectId)
        var runningStart = 0L
        val changed = clips.mapNotNull { entity ->
            val needsUpdate = entity.startTimeMs != runningStart
            val updated = if (needsUpdate) entity.copy(startTimeMs = runningStart) else null
            runningStart += entity.effectiveDurationMs()
            updated
        }
        if (changed.isNotEmpty()) clipDao.updateAll(changed)
    }
}

private fun ClipEntity.effectiveDurationMs(): Long =
    ((trimEndMs ?: sourceDurationMs) - trimStartMs).coerceAtLeast(0L)

private fun ClipEntity.toDomain() = Clip(
    id = id,
    projectId = projectId,
    mediaUri = mediaUri,
    mediaMimeType = mediaMimeType,
    orderIndex = orderIndex,
    startTimeMs = startTimeMs,
    sourceDurationMs = sourceDurationMs,
    trimStartMs = trimStartMs,
    trimEndMs = trimEndMs
)
