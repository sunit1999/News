package com.sunit.news.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sunit.news.feature.bookmarks.BookmarksScreen
import com.sunit.news.feature.home.HomeScreen
import com.sunit.news.feature.preferences.PREFERENCES_HOME_ROUTE
import com.sunit.news.feature.preferences.PreferencesScreen
import com.sunit.news.feature.preferences.composables.REGION_PREFERENCES_ROUTE
import com.sunit.news.feature.preferences.composables.RegionPreferences
import com.sunit.news.feature.preferences.composables.THEME_PREFERENCES_ROUTE
import com.sunit.news.feature.preferences.composables.ThemePreferences

@Composable
fun NewsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = TopLevelDestination.HOME.route) {
        composable(route = TopLevelDestination.HOME.route) {
            HomeScreen(modifier = modifier)
        }
        composable(route = TopLevelDestination.BOOKMARKS.route) {
            BookmarksScreen(modifier = modifier)
        }
        navigation(
            route = PREFERENCES_HOME_ROUTE,
            startDestination = TopLevelDestination.PREFERENCES.route,
        ) {
            composable(route = TopLevelDestination.PREFERENCES.route) {
                PreferencesScreen(
                    navigateToPreference = {
                        navController.navigate(it) {
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(route = THEME_PREFERENCES_ROUTE) {
                ThemePreferences()
            }
            composable(route = REGION_PREFERENCES_ROUTE) {
                RegionPreferences()
            }
        }
    }
}
