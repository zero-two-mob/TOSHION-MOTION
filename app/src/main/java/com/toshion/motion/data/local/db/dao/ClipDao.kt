package com.toshion.motion.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.toshion.motion.data.local.db.entity.ClipEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClipDao {
    @Query("SELECT * FROM clips WHERE projectId = :projectId ORDER BY orderIndex ASC")
    fun observeClips(projectId: Long): Flow<List<ClipEntity>>

    @Query("SELECT * FROM clips WHERE projectId = :projectId ORDER BY orderIndex ASC")
    suspend fun getClips(projectId: Long): List<ClipEntity>

    @Query("SELECT * FROM clips WHERE id = :clipId")
    suspend fun getClip(clipId: Long): ClipEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(clip: ClipEntity): Long

    @Update
    suspend fun update(clip: ClipEntity)

    @Update
    suspend fun updateAll(clips: List<ClipEntity>)

    @Query("DELETE FROM clips WHERE id = :clipId")
    suspend fun delete(clipId: Long)

    /** Used by split (shift by +1 to make room) and ripple delete (shift by
     *  -1 to close the gap) — a single atomic UPDATE rather than a
     *  read-modify-write loop. */
    @Query("UPDATE clips SET orderIndex = orderIndex + :by WHERE projectId = :projectId AND orderIndex >= :fromIndex")
    suspend fun shiftOrderIndicesFrom(projectId: Long, fromIndex: Int, by: Int)
}
