package com.toshion.motion.core.files

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

/**
 * App-scoped storage locations and simple maintenance operations. Everything
 * lives under app-private storage — no broad filesystem access needed for
 * editing/export working files (the Photo Picker handles bringing media in;
 * MediaStore + the Downloads collection handles sending exports out, wired
 * up in Phase 14).
 */
@Singleton
class AppFileManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun projectsDir(): File = File(context.filesDir, "projects").apply { mkdirs() }

    fun projectDir(projectId: Long): File =
        File(projectsDir(), projectId.toString()).apply { mkdirs() }

    fun thumbnailsDir(): File = File(context.cacheDir, "thumbnails").apply { mkdirs() }

    fun crashReportsDir(): File = File(context.filesDir, "crash_reports").apply { mkdirs() }

    fun exportsDir(): File =
        File(context.getExternalFilesDir(null), "exports").apply { mkdirs() }

    fun cacheSizeBytes(): Long =
        context.cacheDir.walkTopDown().filter { it.isFile }.sumOf { it.length() }

    fun projectsSizeBytes(): Long =
        projectsDir().walkTopDown().filter { it.isFile }.sumOf { it.length() }

    fun exportsSizeBytes(): Long =
        exportsDir().walkTopDown().filter { it.isFile }.sumOf { it.length() }

    fun clearCache(): Boolean = context.cacheDir.deleteRecursively()
}
