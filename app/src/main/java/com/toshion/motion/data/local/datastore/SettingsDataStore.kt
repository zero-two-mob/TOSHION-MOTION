package com.toshion.motion.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

private val Context.settingsDataStore by preferencesDataStore(name = "motionforge_settings")

object SettingsKeys {
    val THEME_MODE = stringPreferencesKey("theme_mode")
}

@Singleton
class SettingsDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    val data: Flow<androidx.datastore.preferences.core.Preferences> = context.settingsDataStore.data

    suspend fun setThemeMode(value: String) {
        context.settingsDataStore.edit { prefs -> prefs[SettingsKeys.THEME_MODE] = value }
    }
}
