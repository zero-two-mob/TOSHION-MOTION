package com.toshion.motion.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toshion.motion.data.local.db.dao.ClipDao
import com.toshion.motion.data.local.db.dao.ProjectDao
import com.toshion.motion.data.local.db.entity.ClipEntity
import com.toshion.motion.data.local.db.entity.ProjectEntity

/**
 * version 2: added the `clips` table (Phase 4) via MIGRATION_1_2, wired up
 * in DatabaseModule. exportSchema stays false for now — turn it on (and
 * commit the generated JSON under app/schemas) once schema history needs
 * to be diffed/reviewed rather than just migrated forward.
 */
@Database(
    entities = [ProjectEntity::class, ClipEntity::class],
    version = 2,
    exportSchema = false
)
abstract class ToshionMotionDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
    abstract fun clipDao(): ClipDao
}
