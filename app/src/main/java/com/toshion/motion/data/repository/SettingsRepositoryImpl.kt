package com.toshion.motion.data.repository

import com.toshion.motion.data.local.datastore.SettingsDataStore
import com.toshion.motion.data.local.datastore.SettingsKeys
import com.toshion.motion.domain.model.ThemePreference
import com.toshion.motion.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val settingsDataStore: SettingsDataStore
) : SettingsRepository {

    override val themeMode: Flow<ThemePreference> = settingsDataStore.data.map { prefs ->
        val raw = prefs[SettingsKeys.THEME_MODE]
        if (raw == null) {
            ThemePreference.DARK
        } else {
            runCatching { ThemePreference.valueOf(raw) }.getOrDefault(ThemePreference.DARK)
        }
    }

    override suspend fun setThemeMode(mode: ThemePreference) {
        settingsDataStore.setThemeMode(mode.name)
    }
}
