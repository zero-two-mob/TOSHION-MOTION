package com.toshion.motion.domain.repository

import com.toshion.motion.domain.model.ThemePreference
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val themeMode: Flow<ThemePreference>
    suspend fun setThemeMode(mode: ThemePreference)
}
