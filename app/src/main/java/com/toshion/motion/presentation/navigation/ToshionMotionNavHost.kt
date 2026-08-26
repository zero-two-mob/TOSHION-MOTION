package com.toshion.motion.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.toshion.motion.presentation.editor.EditorScreen
import com.toshion.motion.presentation.home.HomeScreen
import com.toshion.motion.presentation.settings.SettingsScreen
import com.toshion.motion.presentation.settings.detail.AboutScreen
import com.toshion.motion.presentation.settings.detail.CrashReportsScreen
import com.toshion.motion.presentation.settings.detail.GpuInfoScreen
import com.toshion.motion.presentation.settings.detail.LicensesScreen
import com.toshion.motion.presentation.settings.detail.PrivacyScreen

@Composable
fun ToshionMotionNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onOpenProject = { projectId ->
                    navController.navigate(Screen.Editor.createRoute(projectId))
                },
                onOpenSettings = { navController.navigate(Screen.Settings.route) }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                onBack = navController::popBackStack,
                onNavigateToGpuInfo = { navController.navigate(Screen.GpuInfo.route) },
                onNavigateToAbout = { navController.navigate(Screen.About.route) },
                onNavigateToLicenses = { navController.navigate(Screen.Licenses.route) },
                onNavigateToPrivacy = { navController.navigate(Screen.Privacy.route) },
                onNavigateToCrashReports = { navController.navigate(Screen.CrashReports.route) }
            )
        }

        composable(Screen.GpuInfo.route) {
            GpuInfoScreen(onBack = navController::popBackStack)
        }
        composable(Screen.About.route) {
            AboutScreen(onBack = navController::popBackStack)
        }
        composable(Screen.Licenses.route) {
            LicensesScreen(onBack = navController::popBackStack)
        }
        composable(Screen.Privacy.route) {
            PrivacyScreen(onBack = navController::popBackStack)
        }
        composable(Screen.CrashReports.route) {
            CrashReportsScreen(onBack = navController::popBackStack)
        }

        composable(
            route = Screen.Editor.route,
            arguments = listOf(navArgument(Screen.Editor.ARG_PROJECT_ID) { type = NavType.LongType })
        ) {
            EditorScreen(onBack = navController::popBackStack)
        }
    }
}
