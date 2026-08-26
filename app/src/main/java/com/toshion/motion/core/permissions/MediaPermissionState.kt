package com.toshion.motion.core.permissions

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

data class MediaPermissionState(
    val isGranted: Boolean,
    val request: () -> Unit
)

@Composable
fun rememberMediaPermissionState(
    onResult: (granted: Boolean) -> Unit = {}
): MediaPermissionState {
    val context = LocalContext.current
    val permissions = remember { MediaPermissions.readMediaPermissions() }

    var allGranted by remember {
        mutableStateOf(
            permissions.all {
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
            }
        )
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        allGranted = results.values.all { it }
        onResult(allGranted)
    }

    return remember(allGranted) {
        MediaPermissionState(
            isGranted = allGranted,
            request = { launcher.launch(permissions) }
        )
    }
}
