package com.sunit.news.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.sunit.news.R

enum class TopLevelDestination(
    val route: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val iconTextId: Int,
    @StringRes val titleTextId: Int,
) {
    HOME(
        route = "home",
        unselectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Rounded.Home,
        iconTextId = R.string.home_title,
        titleTextId = R.string.home_icon_label
    ),
    SEARCH(
        route = "search",
        unselectedIcon = Icons.Outlined.Search,
        selectedIcon = Icons.Rounded.Search,
        iconTextId = R.string.search_title,
        titleTextId = R.string.search_title
    ),
    BOOKMARKS(
        route = "bookmarks",
        unselectedIcon = Icons.Outlined.Bookmarks,
        selectedIcon = Icons.Rounded.Bookmarks,
        iconTextId = R.string.bookmarks_title,
        titleTextId = R.string.bookmarks_icon_label
    ),
    PREFERENCES(
        route = "preferences",
        unselectedIcon = Icons.Outlined.Settings,
        selectedIcon = Icons.Rounded.Settings,
        iconTextId = R.string.preferences_title,
        titleTextId = R.string.preferences_icon_label
    )
}
