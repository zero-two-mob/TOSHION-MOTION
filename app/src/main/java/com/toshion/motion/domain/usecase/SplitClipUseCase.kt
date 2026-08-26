package com.toshion.motion.domain.usecase

import com.toshion.motion.domain.model.Clip
import com.toshion.motion.domain.repository.ClipRepository
import javax.inject.Inject

class SplitClipUseCase @Inject constructor(
    private val clipRepository: ClipRepository
) {
    /**
     * [timelinePositionMs] is a global timeline position (e.g. the
     * player's current position) — this use case is what converts it into
     * the source-relative position the repository expects, so callers
     * never have to think about that unit conversion themselves.
     */
    suspend operator fun invoke(clip: Clip, timelinePositionMs: Long) {
        val offsetIntoClip = timelinePositionMs - clip.startTimeMs
        if (offsetIntoClip <= 0L || offsetIntoClip >= clip.durationMs) return
        clipRepository.splitClip(clip.id, clip.trimStartMs + offsetIntoClip)
    }
}
