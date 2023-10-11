package com.sunit.news.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.sunit.news.ui.composables.NewsBottomBar
import com.sunit.news.ui.composables.NewsTopBar
import com.sunit.news.ui.navigation.NewsNavHost
import com.sunit.news.ui.navigation.TopLevelDestination

@Composable
fun NewsApp() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { NewsTopBar() },
        bottomBar = {
            NewsBottomBar(
                destinations = TopLevelDestination.values().asList(),
                navController = navController,
                onNavigateToDestination = {
                    navController.navigate(route = it.route, builder = {
                        popUpTo(TopLevelDestination.HOME.route) { inclusive = false }
                        launchSingleTop = true
                        restoreState = true
                    })
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            NewsNavHost(
                navController = navController,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}
