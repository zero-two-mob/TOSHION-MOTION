package com.toshion.motion.presentation.navigation

sealed class Screen(val route: String) {
    /** Now a single screen: New Project card + project list together. */
    data object Home : Screen("home")

    data object Settings : Screen("settings")

    // Settings detail drill-downs — pushed on top of Settings.
    data object GpuInfo : Screen("settings/gpu_info")
    data object About : Screen("settings/about")
    data object Licenses : Screen("settings/licenses")
    data object Privacy : Screen("settings/privacy")
    data object CrashReports : Screen("settings/crash_reports")

    /** Wired up for real in Phase 3 (Editing Workspace). */
    data object Editor : Screen("editor/{projectId}") {
        const val ARG_PROJECT_ID = "projectId"
        fun createRoute(projectId: Long) = "editor/$projectId"
    }
}
