package com.toshion.motion.domain.usecase

import com.toshion.motion.domain.model.Clip
import com.toshion.motion.domain.repository.ClipRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetClipsUseCase @Inject constructor(
    private val clipRepository: ClipRepository
) {
    operator fun invoke(projectId: Long): Flow<List<Clip>> = clipRepository.observeClips(projectId)
}
