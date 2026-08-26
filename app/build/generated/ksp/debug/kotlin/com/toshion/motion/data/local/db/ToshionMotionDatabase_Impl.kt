package com.toshion.motion.`data`.local.db

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.toshion.motion.`data`.local.db.dao.ClipDao
import com.toshion.motion.`data`.local.db.dao.ClipDao_Impl
import com.toshion.motion.`data`.local.db.dao.ProjectDao
import com.toshion.motion.`data`.local.db.dao.ProjectDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ToshionMotionDatabase_Impl : ToshionMotionDatabase() {
  private val _projectDao: Lazy<ProjectDao> = lazy {
    ProjectDao_Impl(this)
  }

  private val _clipDao: Lazy<ClipDao> = lazy {
    ClipDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(2,
        "90d89a4dbf8c287132097d38f1afa987", "0f070fe881aa3735af060d572f09ffe4") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `projects` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `resolutionWidth` INTEGER NOT NULL, `resolutionHeight` INTEGER NOT NULL, `fps` INTEGER NOT NULL, `createdAtEpochMillis` INTEGER NOT NULL, `updatedAtEpochMillis` INTEGER NOT NULL, `thumbnailPath` TEXT, `durationMs` INTEGER NOT NULL, `initialMediaUri` TEXT, `initialMediaMimeType` TEXT)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `clips` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `projectId` INTEGER NOT NULL, `mediaUri` TEXT NOT NULL, `mediaMimeType` TEXT, `orderIndex` INTEGER NOT NULL, `startTimeMs` INTEGER NOT NULL, `sourceDurationMs` INTEGER NOT NULL, `trimStartMs` INTEGER NOT NULL, `trimEndMs` INTEGER)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '90d89a4dbf8c287132097d38f1afa987')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `projects`")
        connection.execSQL("DROP TABLE IF EXISTS `clips`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsProjects: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsProjects.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsProjects.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsProjects.put("resolutionWidth", TableInfo.Column("resolutionWidth", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProjects.put("resolutionHeight", TableInfo.Column("resolutionHeight", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProjects.put("fps", TableInfo.Column("fps", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsProjects.put("createdAtEpochMillis", TableInfo.Column("createdAtEpochMillis",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProjects.put("updatedAtEpochMillis", TableInfo.Column("updatedAtEpochMillis",
            "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProjects.put("thumbnailPath", TableInfo.Column("thumbnailPath", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProjects.put("durationMs", TableInfo.Column("durationMs", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsProjects.put("initialMediaUri", TableInfo.Column("initialMediaUri", "TEXT", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProjects.put("initialMediaMimeType", TableInfo.Column("initialMediaMimeType",
            "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysProjects: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesProjects: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoProjects: TableInfo = TableInfo("projects", _columnsProjects, _foreignKeysProjects,
            _indicesProjects)
        val _existingProjects: TableInfo = read(connection, "projects")
        if (!_infoProjects.equals(_existingProjects)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |projects(com.toshion.motion.data.local.db.entity.ProjectEntity).
              | Expected:
              |""".trimMargin() + _infoProjects + """
              |
              | Found:
              |""".trimMargin() + _existingProjects)
        }
        val _columnsClips: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsClips.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsClips.put("projectId", TableInfo.Column("projectId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsClips.put("mediaUri", TableInfo.Column("mediaUri", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsClips.put("mediaMimeType", TableInfo.Column("mediaMimeType", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsClips.put("orderIndex", TableInfo.Column("orderIndex", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsClips.put("startTimeMs", TableInfo.Column("startTimeMs", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsClips.put("sourceDurationMs", TableInfo.Column("sourceDurationMs", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsClips.put("trimStartMs", TableInfo.Column("trimStartMs", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsClips.put("trimEndMs", TableInfo.Column("trimEndMs", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysClips: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesClips: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoClips: TableInfo = TableInfo("clips", _columnsClips, _foreignKeysClips,
            _indicesClips)
        val _existingClips: TableInfo = read(connection, "clips")
        if (!_infoClips.equals(_existingClips)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |clips(com.toshion.motion.data.local.db.entity.ClipEntity).
              | Expected:
              |""".trimMargin() + _infoClips + """
              |
              | Found:
              |""".trimMargin() + _existingClips)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "projects", "clips")
  }

  public override fun clearAllTables() {
    super.performClear(false, "projects", "clips")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(ProjectDao::class, ProjectDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ClipDao::class, ClipDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun projectDao(): ProjectDao = _projectDao.value

  public override fun clipDao(): ClipDao = _clipDao.value
}
