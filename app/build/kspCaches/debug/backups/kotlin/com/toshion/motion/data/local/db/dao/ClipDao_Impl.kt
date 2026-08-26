package com.toshion.motion.`data`.local.db.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.toshion.motion.`data`.local.db.entity.ClipEntity
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ClipDao_Impl(
  __db: RoomDatabase,
) : ClipDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfClipEntity: EntityInsertAdapter<ClipEntity>

  private val __updateAdapterOfClipEntity: EntityDeleteOrUpdateAdapter<ClipEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfClipEntity = object : EntityInsertAdapter<ClipEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `clips` (`id`,`projectId`,`mediaUri`,`mediaMimeType`,`orderIndex`,`startTimeMs`,`sourceDurationMs`,`trimStartMs`,`trimEndMs`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ClipEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.projectId)
        statement.bindText(3, entity.mediaUri)
        val _tmpMediaMimeType: String? = entity.mediaMimeType
        if (_tmpMediaMimeType == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpMediaMimeType)
        }
        statement.bindLong(5, entity.orderIndex.toLong())
        statement.bindLong(6, entity.startTimeMs)
        statement.bindLong(7, entity.sourceDurationMs)
        statement.bindLong(8, entity.trimStartMs)
        val _tmpTrimEndMs: Long? = entity.trimEndMs
        if (_tmpTrimEndMs == null) {
          statement.bindNull(9)
        } else {
          statement.bindLong(9, _tmpTrimEndMs)
        }
      }
    }
    this.__updateAdapterOfClipEntity = object : EntityDeleteOrUpdateAdapter<ClipEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `clips` SET `id` = ?,`projectId` = ?,`mediaUri` = ?,`mediaMimeType` = ?,`orderIndex` = ?,`startTimeMs` = ?,`sourceDurationMs` = ?,`trimStartMs` = ?,`trimEndMs` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ClipEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.projectId)
        statement.bindText(3, entity.mediaUri)
        val _tmpMediaMimeType: String? = entity.mediaMimeType
        if (_tmpMediaMimeType == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpMediaMimeType)
        }
        statement.bindLong(5, entity.orderIndex.toLong())
        statement.bindLong(6, entity.startTimeMs)
        statement.bindLong(7, entity.sourceDurationMs)
        statement.bindLong(8, entity.trimStartMs)
        val _tmpTrimEndMs: Long? = entity.trimEndMs
        if (_tmpTrimEndMs == null) {
          statement.bindNull(9)
        } else {
          statement.bindLong(9, _tmpTrimEndMs)
        }
        statement.bindLong(10, entity.id)
      }
    }
  }

  public override suspend fun insert(clip: ClipEntity): Long = performSuspending(__db, false, true)
      { _connection ->
    val _result: Long = __insertAdapterOfClipEntity.insertAndReturnId(_connection, clip)
    _result
  }

  public override suspend fun update(clip: ClipEntity): Unit = performSuspending(__db, false, true)
      { _connection ->
    __updateAdapterOfClipEntity.handle(_connection, clip)
  }

  public override suspend fun updateAll(clips: List<ClipEntity>): Unit = performSuspending(__db,
      false, true) { _connection ->
    __updateAdapterOfClipEntity.handleMultiple(_connection, clips)
  }

  public override fun observeClips(projectId: Long): Flow<List<ClipEntity>> {
    val _sql: String = "SELECT * FROM clips WHERE projectId = ? ORDER BY orderIndex ASC"
    return createFlow(__db, false, arrayOf("clips")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, projectId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProjectId: Int = getColumnIndexOrThrow(_stmt, "projectId")
        val _columnIndexOfMediaUri: Int = getColumnIndexOrThrow(_stmt, "mediaUri")
        val _columnIndexOfMediaMimeType: Int = getColumnIndexOrThrow(_stmt, "mediaMimeType")
        val _columnIndexOfOrderIndex: Int = getColumnIndexOrThrow(_stmt, "orderIndex")
        val _columnIndexOfStartTimeMs: Int = getColumnIndexOrThrow(_stmt, "startTimeMs")
        val _columnIndexOfSourceDurationMs: Int = getColumnIndexOrThrow(_stmt, "sourceDurationMs")
        val _columnIndexOfTrimStartMs: Int = getColumnIndexOrThrow(_stmt, "trimStartMs")
        val _columnIndexOfTrimEndMs: Int = getColumnIndexOrThrow(_stmt, "trimEndMs")
        val _result: MutableList<ClipEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ClipEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProjectId: Long
          _tmpProjectId = _stmt.getLong(_columnIndexOfProjectId)
          val _tmpMediaUri: String
          _tmpMediaUri = _stmt.getText(_columnIndexOfMediaUri)
          val _tmpMediaMimeType: String?
          if (_stmt.isNull(_columnIndexOfMediaMimeType)) {
            _tmpMediaMimeType = null
          } else {
            _tmpMediaMimeType = _stmt.getText(_columnIndexOfMediaMimeType)
          }
          val _tmpOrderIndex: Int
          _tmpOrderIndex = _stmt.getLong(_columnIndexOfOrderIndex).toInt()
          val _tmpStartTimeMs: Long
          _tmpStartTimeMs = _stmt.getLong(_columnIndexOfStartTimeMs)
          val _tmpSourceDurationMs: Long
          _tmpSourceDurationMs = _stmt.getLong(_columnIndexOfSourceDurationMs)
          val _tmpTrimStartMs: Long
          _tmpTrimStartMs = _stmt.getLong(_columnIndexOfTrimStartMs)
          val _tmpTrimEndMs: Long?
          if (_stmt.isNull(_columnIndexOfTrimEndMs)) {
            _tmpTrimEndMs = null
          } else {
            _tmpTrimEndMs = _stmt.getLong(_columnIndexOfTrimEndMs)
          }
          _item =
              ClipEntity(_tmpId,_tmpProjectId,_tmpMediaUri,_tmpMediaMimeType,_tmpOrderIndex,_tmpStartTimeMs,_tmpSourceDurationMs,_tmpTrimStartMs,_tmpTrimEndMs)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getClips(projectId: Long): List<ClipEntity> {
    val _sql: String = "SELECT * FROM clips WHERE projectId = ? ORDER BY orderIndex ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, projectId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProjectId: Int = getColumnIndexOrThrow(_stmt, "projectId")
        val _columnIndexOfMediaUri: Int = getColumnIndexOrThrow(_stmt, "mediaUri")
        val _columnIndexOfMediaMimeType: Int = getColumnIndexOrThrow(_stmt, "mediaMimeType")
        val _columnIndexOfOrderIndex: Int = getColumnIndexOrThrow(_stmt, "orderIndex")
        val _columnIndexOfStartTimeMs: Int = getColumnIndexOrThrow(_stmt, "startTimeMs")
        val _columnIndexOfSourceDurationMs: Int = getColumnIndexOrThrow(_stmt, "sourceDurationMs")
        val _columnIndexOfTrimStartMs: Int = getColumnIndexOrThrow(_stmt, "trimStartMs")
        val _columnIndexOfTrimEndMs: Int = getColumnIndexOrThrow(_stmt, "trimEndMs")
        val _result: MutableList<ClipEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ClipEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProjectId: Long
          _tmpProjectId = _stmt.getLong(_columnIndexOfProjectId)
          val _tmpMediaUri: String
          _tmpMediaUri = _stmt.getText(_columnIndexOfMediaUri)
          val _tmpMediaMimeType: String?
          if (_stmt.isNull(_columnIndexOfMediaMimeType)) {
            _tmpMediaMimeType = null
          } else {
            _tmpMediaMimeType = _stmt.getText(_columnIndexOfMediaMimeType)
          }
          val _tmpOrderIndex: Int
          _tmpOrderIndex = _stmt.getLong(_columnIndexOfOrderIndex).toInt()
          val _tmpStartTimeMs: Long
          _tmpStartTimeMs = _stmt.getLong(_columnIndexOfStartTimeMs)
          val _tmpSourceDurationMs: Long
          _tmpSourceDurationMs = _stmt.getLong(_columnIndexOfSourceDurationMs)
          val _tmpTrimStartMs: Long
          _tmpTrimStartMs = _stmt.getLong(_columnIndexOfTrimStartMs)
          val _tmpTrimEndMs: Long?
          if (_stmt.isNull(_columnIndexOfTrimEndMs)) {
            _tmpTrimEndMs = null
          } else {
            _tmpTrimEndMs = _stmt.getLong(_columnIndexOfTrimEndMs)
          }
          _item =
              ClipEntity(_tmpId,_tmpProjectId,_tmpMediaUri,_tmpMediaMimeType,_tmpOrderIndex,_tmpStartTimeMs,_tmpSourceDurationMs,_tmpTrimStartMs,_tmpTrimEndMs)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getClip(clipId: Long): ClipEntity? {
    val _sql: String = "SELECT * FROM clips WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, clipId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProjectId: Int = getColumnIndexOrThrow(_stmt, "projectId")
        val _columnIndexOfMediaUri: Int = getColumnIndexOrThrow(_stmt, "mediaUri")
        val _columnIndexOfMediaMimeType: Int = getColumnIndexOrThrow(_stmt, "mediaMimeType")
        val _columnIndexOfOrderIndex: Int = getColumnIndexOrThrow(_stmt, "orderIndex")
        val _columnIndexOfStartTimeMs: Int = getColumnIndexOrThrow(_stmt, "startTimeMs")
        val _columnIndexOfSourceDurationMs: Int = getColumnIndexOrThrow(_stmt, "sourceDurationMs")
        val _columnIndexOfTrimStartMs: Int = getColumnIndexOrThrow(_stmt, "trimStartMs")
        val _columnIndexOfTrimEndMs: Int = getColumnIndexOrThrow(_stmt, "trimEndMs")
        val _result: ClipEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProjectId: Long
          _tmpProjectId = _stmt.getLong(_columnIndexOfProjectId)
          val _tmpMediaUri: String
          _tmpMediaUri = _stmt.getText(_columnIndexOfMediaUri)
          val _tmpMediaMimeType: String?
          if (_stmt.isNull(_columnIndexOfMediaMimeType)) {
            _tmpMediaMimeType = null
          } else {
            _tmpMediaMimeType = _stmt.getText(_columnIndexOfMediaMimeType)
          }
          val _tmpOrderIndex: Int
          _tmpOrderIndex = _stmt.getLong(_columnIndexOfOrderIndex).toInt()
          val _tmpStartTimeMs: Long
          _tmpStartTimeMs = _stmt.getLong(_columnIndexOfStartTimeMs)
          val _tmpSourceDurationMs: Long
          _tmpSourceDurationMs = _stmt.getLong(_columnIndexOfSourceDurationMs)
          val _tmpTrimStartMs: Long
          _tmpTrimStartMs = _stmt.getLong(_columnIndexOfTrimStartMs)
          val _tmpTrimEndMs: Long?
          if (_stmt.isNull(_columnIndexOfTrimEndMs)) {
            _tmpTrimEndMs = null
          } else {
            _tmpTrimEndMs = _stmt.getLong(_columnIndexOfTrimEndMs)
          }
          _result =
              ClipEntity(_tmpId,_tmpProjectId,_tmpMediaUri,_tmpMediaMimeType,_tmpOrderIndex,_tmpStartTimeMs,_tmpSourceDurationMs,_tmpTrimStartMs,_tmpTrimEndMs)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun delete(clipId: Long) {
    val _sql: String = "DELETE FROM clips WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, clipId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun shiftOrderIndicesFrom(
    projectId: Long,
    fromIndex: Int,
    `by`: Int,
  ) {
    val _sql: String =
        "UPDATE clips SET orderIndex = orderIndex + ? WHERE projectId = ? AND orderIndex >= ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, by.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, projectId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, fromIndex.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
