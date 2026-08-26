package com.toshion.motion.domain.usecase

import com.toshion.motion.domain.repository.ClipRepository
import javax.inject.Inject

class DeleteClipUseCase @Inject constructor(
    private val clipRepository: ClipRepository
) {
    suspend operator fun invoke(clipId: Long) = clipRepository.deleteClip(clipId)
}
