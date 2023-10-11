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
        composable(TopLevelDestination.HOME.route) {
            HomeScreen(modifier = modifier)
        }
        composable(TopLevelDestination.BOOKMARKS.route) {
            BookmarksScreen(modifier = modifier)
        }
        navigation(
            startDestination = PREFERENCES_HOME_ROUTE,
            route = TopLevelDestination.PREFERENCES.route
        ) {
            composable(PREFERENCES_HOME_ROUTE) {
                PreferencesScreen(
                    navigateToPreference = {
                        navController.navigate(it) {
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(THEME_PREFERENCES_ROUTE) {
                ThemePreferences()
            }
            composable(REGION_PREFERENCES_ROUTE) {
                RegionPreferences()
            }
        }
    }
}
