package com.toshion.motion.`data`.local.db.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.toshion.motion.`data`.local.db.entity.ProjectEntity
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
public class ProjectDao_Impl(
  __db: RoomDatabase,
) : ProjectDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfProjectEntity: EntityInsertAdapter<ProjectEntity>

  private val __updateAdapterOfProjectEntity: EntityDeleteOrUpdateAdapter<ProjectEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfProjectEntity = object : EntityInsertAdapter<ProjectEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `projects` (`id`,`name`,`resolutionWidth`,`resolutionHeight`,`fps`,`createdAtEpochMillis`,`updatedAtEpochMillis`,`thumbnailPath`,`durationMs`,`initialMediaUri`,`initialMediaMimeType`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ProjectEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        statement.bindLong(3, entity.resolutionWidth.toLong())
        statement.bindLong(4, entity.resolutionHeight.toLong())
        statement.bindLong(5, entity.fps.toLong())
        statement.bindLong(6, entity.createdAtEpochMillis)
        statement.bindLong(7, entity.updatedAtEpochMillis)
        val _tmpThumbnailPath: String? = entity.thumbnailPath
        if (_tmpThumbnailPath == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpThumbnailPath)
        }
        statement.bindLong(9, entity.durationMs)
        val _tmpInitialMediaUri: String? = entity.initialMediaUri
        if (_tmpInitialMediaUri == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpInitialMediaUri)
        }
        val _tmpInitialMediaMimeType: String? = entity.initialMediaMimeType
        if (_tmpInitialMediaMimeType == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpInitialMediaMimeType)
        }
      }
    }
    this.__updateAdapterOfProjectEntity = object : EntityDeleteOrUpdateAdapter<ProjectEntity>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `projects` SET `id` = ?,`name` = ?,`resolutionWidth` = ?,`resolutionHeight` = ?,`fps` = ?,`createdAtEpochMillis` = ?,`updatedAtEpochMillis` = ?,`thumbnailPath` = ?,`durationMs` = ?,`initialMediaUri` = ?,`initialMediaMimeType` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ProjectEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        statement.bindLong(3, entity.resolutionWidth.toLong())
        statement.bindLong(4, entity.resolutionHeight.toLong())
        statement.bindLong(5, entity.fps.toLong())
        statement.bindLong(6, entity.createdAtEpochMillis)
        statement.bindLong(7, entity.updatedAtEpochMillis)
        val _tmpThumbnailPath: String? = entity.thumbnailPath
        if (_tmpThumbnailPath == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpThumbnailPath)
        }
        statement.bindLong(9, entity.durationMs)
        val _tmpInitialMediaUri: String? = entity.initialMediaUri
        if (_tmpInitialMediaUri == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpInitialMediaUri)
        }
        val _tmpInitialMediaMimeType: String? = entity.initialMediaMimeType
        if (_tmpInitialMediaMimeType == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpInitialMediaMimeType)
        }
        statement.bindLong(12, entity.id)
      }
    }
  }

  public override suspend fun insert(project: ProjectEntity): Long = performSuspending(__db, false,
      true) { _connection ->
    val _result: Long = __insertAdapterOfProjectEntity.insertAndReturnId(_connection, project)
    _result
  }

  public override suspend fun update(project: ProjectEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __updateAdapterOfProjectEntity.handle(_connection, project)
  }

  public override fun observeProjects(): Flow<List<ProjectEntity>> {
    val _sql: String = "SELECT * FROM projects ORDER BY updatedAtEpochMillis DESC"
    return createFlow(__db, false, arrayOf("projects")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfResolutionWidth: Int = getColumnIndexOrThrow(_stmt, "resolutionWidth")
        val _columnIndexOfResolutionHeight: Int = getColumnIndexOrThrow(_stmt, "resolutionHeight")
        val _columnIndexOfFps: Int = getColumnIndexOrThrow(_stmt, "fps")
        val _columnIndexOfCreatedAtEpochMillis: Int = getColumnIndexOrThrow(_stmt,
            "createdAtEpochMillis")
        val _columnIndexOfUpdatedAtEpochMillis: Int = getColumnIndexOrThrow(_stmt,
            "updatedAtEpochMillis")
        val _columnIndexOfThumbnailPath: Int = getColumnIndexOrThrow(_stmt, "thumbnailPath")
        val _columnIndexOfDurationMs: Int = getColumnIndexOrThrow(_stmt, "durationMs")
        val _columnIndexOfInitialMediaUri: Int = getColumnIndexOrThrow(_stmt, "initialMediaUri")
        val _columnIndexOfInitialMediaMimeType: Int = getColumnIndexOrThrow(_stmt,
            "initialMediaMimeType")
        val _result: MutableList<ProjectEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ProjectEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpResolutionWidth: Int
          _tmpResolutionWidth = _stmt.getLong(_columnIndexOfResolutionWidth).toInt()
          val _tmpResolutionHeight: Int
          _tmpResolutionHeight = _stmt.getLong(_columnIndexOfResolutionHeight).toInt()
          val _tmpFps: Int
          _tmpFps = _stmt.getLong(_columnIndexOfFps).toInt()
          val _tmpCreatedAtEpochMillis: Long
          _tmpCreatedAtEpochMillis = _stmt.getLong(_columnIndexOfCreatedAtEpochMillis)
          val _tmpUpdatedAtEpochMillis: Long
          _tmpUpdatedAtEpochMillis = _stmt.getLong(_columnIndexOfUpdatedAtEpochMillis)
          val _tmpThumbnailPath: String?
          if (_stmt.isNull(_columnIndexOfThumbnailPath)) {
            _tmpThumbnailPath = null
          } else {
            _tmpThumbnailPath = _stmt.getText(_columnIndexOfThumbnailPath)
          }
          val _tmpDurationMs: Long
          _tmpDurationMs = _stmt.getLong(_columnIndexOfDurationMs)
          val _tmpInitialMediaUri: String?
          if (_stmt.isNull(_columnIndexOfInitialMediaUri)) {
            _tmpInitialMediaUri = null
          } else {
            _tmpInitialMediaUri = _stmt.getText(_columnIndexOfInitialMediaUri)
          }
          val _tmpInitialMediaMimeType: String?
          if (_stmt.isNull(_columnIndexOfInitialMediaMimeType)) {
            _tmpInitialMediaMimeType = null
          } else {
            _tmpInitialMediaMimeType = _stmt.getText(_columnIndexOfInitialMediaMimeType)
          }
          _item =
              ProjectEntity(_tmpId,_tmpName,_tmpResolutionWidth,_tmpResolutionHeight,_tmpFps,_tmpCreatedAtEpochMillis,_tmpUpdatedAtEpochMillis,_tmpThumbnailPath,_tmpDurationMs,_tmpInitialMediaUri,_tmpInitialMediaMimeType)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getProject(id: Long): ProjectEntity? {
    val _sql: String = "SELECT * FROM projects WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfResolutionWidth: Int = getColumnIndexOrThrow(_stmt, "resolutionWidth")
        val _columnIndexOfResolutionHeight: Int = getColumnIndexOrThrow(_stmt, "resolutionHeight")
        val _columnIndexOfFps: Int = getColumnIndexOrThrow(_stmt, "fps")
        val _columnIndexOfCreatedAtEpochMillis: Int = getColumnIndexOrThrow(_stmt,
            "createdAtEpochMillis")
        val _columnIndexOfUpdatedAtEpochMillis: Int = getColumnIndexOrThrow(_stmt,
            "updatedAtEpochMillis")
        val _columnIndexOfThumbnailPath: Int = getColumnIndexOrThrow(_stmt, "thumbnailPath")
        val _columnIndexOfDurationMs: Int = getColumnIndexOrThrow(_stmt, "durationMs")
        val _columnIndexOfInitialMediaUri: Int = getColumnIndexOrThrow(_stmt, "initialMediaUri")
        val _columnIndexOfInitialMediaMimeType: Int = getColumnIndexOrThrow(_stmt,
            "initialMediaMimeType")
        val _result: ProjectEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpResolutionWidth: Int
          _tmpResolutionWidth = _stmt.getLong(_columnIndexOfResolutionWidth).toInt()
          val _tmpResolutionHeight: Int
          _tmpResolutionHeight = _stmt.getLong(_columnIndexOfResolutionHeight).toInt()
          val _tmpFps: Int
          _tmpFps = _stmt.getLong(_columnIndexOfFps).toInt()
          val _tmpCreatedAtEpochMillis: Long
          _tmpCreatedAtEpochMillis = _stmt.getLong(_columnIndexOfCreatedAtEpochMillis)
          val _tmpUpdatedAtEpochMillis: Long
          _tmpUpdatedAtEpochMillis = _stmt.getLong(_columnIndexOfUpdatedAtEpochMillis)
          val _tmpThumbnailPath: String?
          if (_stmt.isNull(_columnIndexOfThumbnailPath)) {
            _tmpThumbnailPath = null
          } else {
            _tmpThumbnailPath = _stmt.getText(_columnIndexOfThumbnailPath)
          }
          val _tmpDurationMs: Long
          _tmpDurationMs = _stmt.getLong(_columnIndexOfDurationMs)
          val _tmpInitialMediaUri: String?
          if (_stmt.isNull(_columnIndexOfInitialMediaUri)) {
            _tmpInitialMediaUri = null
          } else {
            _tmpInitialMediaUri = _stmt.getText(_columnIndexOfInitialMediaUri)
          }
          val _tmpInitialMediaMimeType: String?
          if (_stmt.isNull(_columnIndexOfInitialMediaMimeType)) {
            _tmpInitialMediaMimeType = null
          } else {
            _tmpInitialMediaMimeType = _stmt.getText(_columnIndexOfInitialMediaMimeType)
          }
          _result =
              ProjectEntity(_tmpId,_tmpName,_tmpResolutionWidth,_tmpResolutionHeight,_tmpFps,_tmpCreatedAtEpochMillis,_tmpUpdatedAtEpochMillis,_tmpThumbnailPath,_tmpDurationMs,_tmpInitialMediaUri,_tmpInitialMediaMimeType)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun delete(id: Long) {
    val _sql: String = "DELETE FROM projects WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
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
