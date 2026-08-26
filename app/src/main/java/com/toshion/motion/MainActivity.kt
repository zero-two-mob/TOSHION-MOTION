package com.toshion.motion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.toshion.motion.presentation.navigation.ToshionMotionNavHost
import com.toshion.motion.presentation.settings.SettingsViewModel
import com.toshion.motion.ui.theme.ToshionMotionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Activity-scoped, so it survives navigation between destinations —
            // the theme mode needs to wrap the whole NavHost, not one screen.
            val settingsViewModel: SettingsViewModel = hiltViewModel()
            val themeMode by settingsViewModel.themeMode.collectAsStateWithLifecycle()

            ToshionMotionTheme(themeMode = themeMode) {
                ToshionMotionNavHost()
            }
        }
    }
}
