package com.toshion.motion.core.permissions

import android.Manifest
import android.os.Build

/**
 * Central source of truth for which runtime permissions ToshionMotion needs
 * and on which API levels. Note: the Phase 1 media picker uses the system
 * Photo Picker (ActivityResultContracts.PickVisualMedia), which needs none
 * of these — but audio-library browsing (Phase 12) and any future direct
 * MediaStore queries will, so the plumbing goes in now.
 */
object MediaPermissions {

    fun readMediaPermissions(): Array<String> = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ->
            arrayOf(
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_AUDIO
            )
        else -> arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    fun notificationPermission(): Array<String> =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            emptyArray()
        }
}
