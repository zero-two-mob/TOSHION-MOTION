package com.toshion.motion.presentation.settings.detail

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

private data class LicenseEntry(val name: String, val license: String)

// Update this list as new dependencies land in later phases (Media3/ExoPlayer,
// any FFmpeg build, etc.) — everything here is Apache 2.0, which requires
// exactly this kind of attribution.
private val licenses = listOf(
    LicenseEntry("Kotlin", "Apache License 2.0"),
    LicenseEntry("Jetpack Compose", "Apache License 2.0"),
    LicenseEntry("Material Components", "Apache License 2.0"),
    LicenseEntry("AndroidX Room", "Apache License 2.0"),
    LicenseEntry("AndroidX Navigation", "Apache License 2.0"),
    LicenseEntry("AndroidX DataStore", "Apache License 2.0"),
    LicenseEntry("Dagger / Hilt", "Apache License 2.0"),
    LicenseEntry("Kotlin Coroutines", "Apache License 2.0")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LicensesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Licenses") },
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
            items(licenses) { entry ->
                ListItem(
                    headlineContent = { Text(entry.name) },
                    supportingContent = { Text(entry.license) }
                )
                HorizontalDivider()
            }
        }
    }
}
