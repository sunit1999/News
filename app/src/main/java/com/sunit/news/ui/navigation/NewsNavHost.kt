package com.sunit.news.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sunit.news.feature.bookmarks.BookmarksScreen
import com.sunit.news.feature.home.HomeScreen
import com.sunit.news.feature.preferences.PreferencesScreen

@Composable
fun NewsNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = TopLevelDestination.HOME.route) {
        composable(TopLevelDestination.HOME.route) {
            HomeScreen()
        }
        composable(TopLevelDestination.BOOKMARKS.route) {
            BookmarksScreen()
        }
        composable(TopLevelDestination.PREFERENCES.route) {
            PreferencesScreen()
        }
    }
}
