package com.toshion.motion.domain.usecase

import com.toshion.motion.domain.repository.ClipRepository
import javax.inject.Inject

class AddClipUseCase @Inject constructor(
    private val clipRepository: ClipRepository
) {
    suspend operator fun invoke(
        projectId: Long,
        mediaUri: String,
        mediaMimeType: String?,
        sourceDurationMs: Long
    ): Long = clipRepository.addClip(projectId, mediaUri, mediaMimeType, sourceDurationMs)
}
