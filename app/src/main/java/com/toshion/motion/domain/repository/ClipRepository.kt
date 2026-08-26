package com.toshion.motion.domain.repository

import com.toshion.motion.domain.model.Clip
import kotlinx.coroutines.flow.Flow

interface ClipRepository {
    fun observeClips(projectId: Long): Flow<List<Clip>>
    suspend fun getClips(projectId: Long): List<Clip>
    suspend fun addClip(projectId: Long, mediaUri: String, mediaMimeType: String?, sourceDurationMs: Long): Long
    suspend fun updateTrim(clipId: Long, trimStartMs: Long, trimEndMs: Long?)

    /**
     * [atSourcePositionMs] is a position within the SOURCE media (i.e.
     * clip.trimStartMs + offset-into-clip), not a timeline position — the
     * caller is expected to have already subtracted the clip's
     * [com.toshion.motion.domain.model.Clip.startTimeMs].
     */
    suspend fun splitClip(clipId: Long, atSourcePositionMs: Long)

    suspend fun deleteClip(clipId: Long)
}
