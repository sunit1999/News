package com.sunit.news.util

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.sunit.news.ui.navigation.TopLevelDestination

fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
