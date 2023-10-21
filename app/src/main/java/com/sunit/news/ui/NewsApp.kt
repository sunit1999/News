package com.sunit.news.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.sunit.news.R
import com.sunit.news.ui.composables.NewsBottomBar
import com.sunit.news.ui.composables.NewsTopBar
import com.sunit.news.ui.navigation.NewsNavHost
import com.sunit.news.ui.navigation.TopLevelDestination

@Composable
fun NewsApp(
    isOnline: Boolean
) {
    val navController = rememberNavController()
    var currentDestination by rememberSaveable {
        mutableStateOf(TopLevelDestination.HOME)
    }

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            NewsTopBar(text = stringResource(id = currentDestination.titleTextId))
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        bottomBar = {
            NewsBottomBar(
                destinations = TopLevelDestination.values().asList(),
                navController = navController,
                onNavigateToDestination = {
                    navController.navigate(route = it.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    currentDestination = it
                }
            )
        }
    ) { paddingValues ->

        val notConnectedMessage = stringResource(id = R.string.not_connected)
        LaunchedEffect(isOnline) {
            if (!isOnline) {
                snackbarHostState.showSnackbar(
                    message = notConnectedMessage,
                    duration = SnackbarDuration.Indefinite
                )
            }
        }

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
