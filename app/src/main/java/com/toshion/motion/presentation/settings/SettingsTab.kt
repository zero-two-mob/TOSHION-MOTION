package com.toshion.motion.presentation.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.toshion.motion.domain.model.ThemePreference
import kotlin.math.roundToInt

/**
 * Everything here is a genuinely working row except Language and Export
 * Defaults, which are honestly labeled as pending rather than faked —
 * neither has a system to back it yet (per-app locale switching isn't
 * built, and there's no export engine until Phase 14).
 */
@Composable
fun SettingsTab(
    onNavigateToGpuInfo: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToLicenses: () -> Unit,
    onNavigateToPrivacy: () -> Unit,
    onNavigateToCrashReports: () -> Unit,
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    developerOptionsViewModel: DeveloperOptionsViewModel = hiltViewModel()
) {
    val themeMode by settingsViewModel.themeMode.collectAsStateWithLifecycle()
    val cacheSizeBytes by developerOptionsViewModel.cacheSizeBytes.collectAsStateWithLifecycle()

    LazyColumn(modifier = modifier.fillMaxSize()) {
        item { SectionHeader("Theme") }
        item {
            Column(
                modifier = Modifier
                    .selectableGroup()
                    .padding(horizontal = 16.dp)
            ) {
                ThemePreference.entries.forEach { mode ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = themeMode == mode,
                                onClick = { settingsViewModel.setThemeMode(mode) },
                                role = Role.RadioButton
                            )
                            .padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = themeMode == mode, onClick = null)
                        Spacer(Modifier.width(12.dp))
                        Text(mode.label())
                    }
                }
            }
        }

        item { SectionDivider() }
        item { SectionHeader("General") }
        item {
            SettingsRow(
                title = "Language",
                subtitle = "Follows system language — per-app picker not built yet"
            )
        }
        item {
            SettingsRow(
                title = "Export Defaults",
                subtitle = "Arrives with the Export Engine (Phase 14)"
            )
        }
        item {
            SettingsRow(
                title = "GPU & Device Info",
                subtitle = "Renderer, ABI, OpenGL ES / Vulkan support",
                onClick = onNavigateToGpuInfo
            )
        }

        item { SectionDivider() }
        item { SectionHeader("Storage") }
        item {
            SettingsRow(
                title = "Cache",
                subtitle = formatBytes(cacheSizeBytes),
                trailing = {
                    TextButton(onClick = { developerOptionsViewModel.clearCache() }) {
                        Text("Clear")
                    }
                }
            )
        }

        item { SectionDivider() }
        item { SectionHeader("About") }
        item { SettingsRow(title = "Privacy", onClick = onNavigateToPrivacy) }
        item { SettingsRow(title = "About & Version", onClick = onNavigateToAbout) }
        item { SettingsRow(title = "Licenses", onClick = onNavigateToLicenses) }
        item {
            SettingsRow(
                title = "Developer Options",
                subtitle = "Crash reports",
                onClick = onNavigateToCrashReports
            )
        }

        item { Spacer(Modifier.height(24.dp)) }
    }
}

@Composable
private fun SectionHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
private fun SectionDivider() {
    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
}

@Composable
private fun SettingsRow(
    title: String,
    subtitle: String? = null,
    onClick: (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null
) {
    ListItem(
        headlineContent = { Text(title) },
        supportingContent = subtitle?.let { { Text(it) } },
        trailingContent = trailing ?: onClick?.let {
            { Icon(Icons.Default.KeyboardArrowRight, contentDescription = null) }
        },
        modifier = if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier
    )
}

private fun ThemePreference.label(): String = when (this) {
    ThemePreference.LIGHT -> "Light"
    ThemePreference.DARK -> "Dark"
    ThemePreference.AMOLED -> "AMOLED (true black)"
}

private fun formatBytes(bytes: Long): String {
    val kb = bytes / 1024.0
    val mb = kb / 1024.0
    return when {
        mb >= 1.0 -> "${(mb * 10).roundToInt() / 10.0} MB"
        kb >= 1.0 -> "${kb.roundToInt()} KB"
        else -> "$bytes B"
    }
}
