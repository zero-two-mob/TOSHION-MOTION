package com.toshion.motion.di

import android.content.Context
import androidx.room.Room
import com.toshion.motion.data.local.db.MIGRATION_1_2
import com.toshion.motion.data.local.db.ToshionMotionDatabase
import com.toshion.motion.data.local.db.dao.ClipDao
import com.toshion.motion.data.local.db.dao.ProjectDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ToshionMotionDatabase =
        Room.databaseBuilder(
            context,
            ToshionMotionDatabase::class.java,
            "motionforge.db"
        )
            .addMigrations(MIGRATION_1_2)
            .build()

    @Provides
    fun provideProjectDao(database: ToshionMotionDatabase): ProjectDao = database.projectDao()

    @Provides
    fun provideClipDao(database: ToshionMotionDatabase): ClipDao = database.clipDao()
}
