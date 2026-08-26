package com.toshion.motion.domain.model

/**
 * User-selectable theme. Lives in domain (not ui/theme) because Settings
 * persistence needs to reference it without the data/domain layers reaching
 * into Compose. ui/theme.ToshionMotionTheme consumes this directly.
 */
enum class ThemePreference {
    LIGHT,
    DARK,
    AMOLED
}
