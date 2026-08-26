package com.toshion.motion.presentation.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    onNavigateToGpuInfo: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToLicenses: () -> Unit,
    onNavigateToPrivacy: () -> Unit,
    onNavigateToCrashReports: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        SettingsTab(
            onNavigateToGpuInfo = onNavigateToGpuInfo,
            onNavigateToAbout = onNavigateToAbout,
            onNavigateToLicenses = onNavigateToLicenses,
            onNavigateToPrivacy = onNavigateToPrivacy,
            onNavigateToCrashReports = onNavigateToCrashReports,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
