package com.toshion.motion.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.toshion.motion.domain.model.ThemePreference

private val LightColors = lightColorScheme(
    primary = AccentPrimary,
    secondary = AccentSecondary,
    background = LightBackground,
    surface = LightSurface,
    onBackground = LightOnBackground,
    onSurface = LightOnSurface
)

private val DarkColors = darkColorScheme(
    primary = AccentPrimary,
    secondary = AccentSecondary,
    background = DarkBackground,
    surface = DarkSurface,
    onBackground = DarkOnBackground,
    onSurface = DarkOnSurface
)

private val AmoledColors = darkColorScheme(
    primary = AccentPrimary,
    secondary = AccentSecondary,
    background = AmoledBackground,
    surface = AmoledSurface,
    onBackground = AmoledOnBackground,
    onSurface = AmoledOnSurface
)

/**
 * Root theme for ToshionMotion.
 *
 * [themeMode] is driven by the persisted user preference from Settings
 * (SettingsRepository -> DataStore). The default here only applies before
 * that first value arrives.
 */
@Composable
fun ToshionMotionTheme(
    themeMode: ThemePreference = if (isSystemInDarkTheme()) ThemePreference.DARK else ThemePreference.LIGHT,
    content: @Composable () -> Unit
) {
    val colorScheme = when (themeMode) {
        ThemePreference.LIGHT -> LightColors
        ThemePreference.DARK -> DarkColors
        ThemePreference.AMOLED -> AmoledColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = ToshionMotionTypography,
        content = content
    )
}
