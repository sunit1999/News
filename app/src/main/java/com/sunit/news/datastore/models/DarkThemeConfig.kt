package com.sunit.news.datastore.models

import androidx.annotation.StringRes
import com.sunit.news.R

enum class DarkThemeConfig(
    @StringRes val displayName: Int
) {
    SYSTEM_DEFAULT(R.string.system_default_theme),
    DARK(R.string.dark_theme),
    LIGHT(R.string.light_theme)
}
