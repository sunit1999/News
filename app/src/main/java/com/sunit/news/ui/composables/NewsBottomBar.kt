package com.sunit.news.ui.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sunit.news.ui.navigation.TopLevelDestination

@Composable
fun NewsBottomBar(
    destinations: List<TopLevelDestination>,
    navController: NavController,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    NavigationBar {
        destinations.forEach { destination ->
            val isSelected = destination.route == backStackEntry?.destination?.route

            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigateToDestination(destination) },
                label = {
                    Text(text = stringResource(id = destination.iconTextId))
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected)
                            destination.selectedIcon
                        else
                            destination.unselectedIcon,
                        contentDescription = null,
                    )
                },
            )
        }
    }
}
