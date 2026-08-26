package com.toshion.motion.data.local.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Adds the `clips` table for Phase 4's timeline. Purely additive — no
 * existing `projects` data is touched, so anything already saved on a
 * device survives this upgrade. Column types below must match ClipEntity
 * exactly (Room validates the post-migration schema against the entity's
 * annotations and crashes on mismatch), so if ClipEntity's fields ever
 * change, a NEW migration is what changes — never hand-edit this one after
 * it's shipped to a real device.
 */
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `clips` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `projectId` INTEGER NOT NULL,
                `mediaUri` TEXT NOT NULL,
                `mediaMimeType` TEXT,
                `orderIndex` INTEGER NOT NULL,
                `startTimeMs` INTEGER NOT NULL,
                `sourceDurationMs` INTEGER NOT NULL,
                `trimStartMs` INTEGER NOT NULL,
                `trimEndMs` INTEGER
            )
            """.trimIndent()
        )
    }
}
