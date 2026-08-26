package com.toshion.motion.domain.model

/**
 * One piece of media placed on the timeline. Single-track and gapless by
 * design for now: clips always sit back-to-back in [orderIndex] order with
 * no manual free-positioning, which is what gives "nothing can create a
 * gap" (the spec's Magnetic Timeline) for free, and makes ripple delete a
 * simple re-pack rather than collision math. Multi-track (separate video/
 * audio/text/overlay tracks) is Phase 5's Layer Engine.
 */
data class Clip(
    val id: Long = 0L,
    val projectId: Long,
    val mediaUri: String,
    val mediaMimeType: String?,
    val orderIndex: Int,
    /** Position on the timeline, in ms. Derived from preceding clips'
     *  durations, not independently stored/settable. */
    val startTimeMs: Long,
    /** Full source duration, in ms — needed to bound trimming. */
    val sourceDurationMs: Long,
    /** In-point within the source, ms. */
    val trimStartMs: Long = 0L,
    /** Out-point within the source, ms. Null means "to the end". */
    val trimEndMs: Long? = null
) {
    val effectiveTrimEndMs: Long get() = trimEndMs ?: sourceDurationMs
    val durationMs: Long get() = (effectiveTrimEndMs - trimStartMs).coerceAtLeast(0L)
    val endTimeMs: Long get() = startTimeMs + durationMs
}
