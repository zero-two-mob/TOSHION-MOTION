package com.toshion.motion.di

import com.toshion.motion.data.repository.ClipRepositoryImpl
import com.toshion.motion.data.repository.ProjectRepositoryImpl
import com.toshion.motion.data.repository.SettingsRepositoryImpl
import com.toshion.motion.domain.repository.ClipRepository
import com.toshion.motion.domain.repository.ProjectRepository
import com.toshion.motion.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProjectRepository(impl: ProjectRepositoryImpl): ProjectRepository

    @Binds
    @Singleton
    abstract fun bindSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository

    @Binds
    @Singleton
    abstract fun bindClipRepository(impl: ClipRepositoryImpl): ClipRepository
}
