package com.toshion.motion.domain.usecase

import com.toshion.motion.domain.repository.ClipRepository
import javax.inject.Inject

class TrimClipUseCase @Inject constructor(
    private val clipRepository: ClipRepository
) {
    suspend operator fun invoke(clipId: Long, trimStartMs: Long, trimEndMs: Long?) {
        clipRepository.updateTrim(clipId, trimStartMs, trimEndMs)
    }
}
