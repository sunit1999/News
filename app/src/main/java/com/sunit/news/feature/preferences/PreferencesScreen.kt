package com.sunit.news.feature.preferences

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.sunit.news.R
import com.sunit.news.feature.preferences.composables.REGION_PREFERENCES_ROUTE
import com.sunit.news.feature.preferences.composables.THEME_PREFERENCES_ROUTE

const val PREFERENCES_HOME_ROUTE = "preferences_home"

@Composable
fun PreferencesScreen(
    navigateToPreference: (route: String) -> Unit,
    preferencesViewModel: PreferencesViewModel = hiltViewModel()
) {

    Column {
        SettingsListItem(
            text = stringResource(id = R.string.theme_setting),
            leadingIconResVector = Icons.Outlined.DarkMode
        ) {
            navigateToPreference(THEME_PREFERENCES_ROUTE)
        }

        SettingsListItem(
            text = stringResource(id = R.string.region_setting),
            leadingIconResVector = Icons.Outlined.Flag
        ) {
            navigateToPreference(REGION_PREFERENCES_ROUTE)
        }
    }
}

@Composable
fun SettingsListItem(
    text: String,
    leadingIconResVector: ImageVector,
    trailingIconResVector: ImageVector = Icons.Filled.ChevronRight,
    onClick: () -> Unit,
) {
    ListItem(
        headlineContent = { Text(text = text) },
        leadingContent = {
            Icon(imageVector = leadingIconResVector, contentDescription = null)
        },
        trailingContent = {
            Icon(imageVector = trailingIconResVector, contentDescription = null)
        },
        modifier = Modifier.clickable {
            onClick()
        }
    )
    Divider()
}
