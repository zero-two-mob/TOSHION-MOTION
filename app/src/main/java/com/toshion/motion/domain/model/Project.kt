package com.toshion.motion.domain.model

/**
 * Core project entity shared across every phase. Kept intentionally lean —
 * Phase 5's Layer Engine adds a separate Layer model that references a
 * project by id rather than nesting layers in here, so loading a project
 * list never has to pull full timeline data.
 */
data class Project(
    val id: Long = 0L,
    val name: String,
    val resolutionWidth: Int,
    val resolutionHeight: Int,
    val fps: Int,
    val createdAtEpochMillis: Long,
    val updatedAtEpochMillis: Long,
    val thumbnailPath: String? = null,
    val durationMs: Long = 0L,
    /** URI string of the media picked at project-creation time. Phase 3/5's
     *  Editor and Layer Engine read this to seed the first layer — nothing
     *  downstream of "record what the user picked" exists yet, so that's
     *  as far as Phase 2 takes it. */
    val initialMediaUri: String? = null,
    val initialMediaMimeType: String? = null
)
