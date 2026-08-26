package com.toshion.motion.presentation.settings.detail

import android.app.ActivityManager
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GpuInfoScreen(onBack: () -> Unit) {
    val context = LocalContext.current

    val rows = remember {
        val activityManager = ContextCompat.getSystemService(context, ActivityManager::class.java)
        val glEsVersion = activityManager?.deviceConfigurationInfo?.glEsVersion ?: "Unknown"
        val supportsVulkan = context.packageManager.hasSystemFeature(PackageManager.FEATURE_VULKAN_HARDWARE_LEVEL)

        listOf(
            "Device" to "${Build.MANUFACTURER} ${Build.MODEL}",
            "Chipset / board" to Build.HARDWARE,
            "Supported ABIs" to Build.SUPPORTED_ABIS.joinToString(", "),
            "OpenGL ES" to glEsVersion,
            "Vulkan hardware" to if (supportsVulkan) "Supported" else "Not reported",
            "Android version" to "${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})"
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("GPU & Device Info") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(rows) { (label, value) ->
                ListItem(
                    headlineContent = { Text(label) },
                    supportingContent = { Text(value) }
                )
                HorizontalDivider()
            }
            item {
                Text(
                    text = "The preview pipeline's renderer choice (OpenGL ES vs Vulkan) " +
                        "gets decided against this in Phase 3.",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
