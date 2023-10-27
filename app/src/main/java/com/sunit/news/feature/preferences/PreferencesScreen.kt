package com.sunit.news.feature.preferences

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sunit.news.BuildConfig
import com.sunit.news.R
import com.sunit.news.feature.preferences.composables.REGION_PREFERENCES_ROUTE
import com.sunit.news.feature.preferences.composables.THEME_PREFERENCES_ROUTE

const val PREFERENCES_HOME_ROUTE = "preferences_home"

@Composable
fun PreferencesScreen(
    navigateToPreference: (String) -> Unit,
) {
    Column(
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
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

        Spacer(modifier = Modifier.weight(1f))

        LinksRow()
        Text(
            text = "NEWS · v${BuildConfig.VERSION_NAME} · benaam.labs@gmail.com",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.8f)
        )
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

@Composable
fun LinksRow() {
    val uriHandler = LocalUriHandler.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        TextButton(onClick = { uriHandler.openUri(PRIVACY_POLICY_URL) }) {
            Text(text = stringResource(R.string.privacy_policy))
        }

        TextButton(onClick = { uriHandler.openUri(CONTACT_US_URL) }) {
            Text(text = stringResource(R.string.contact_us))
        }

        TextButton(onClick = { uriHandler.openUri(SOURCE_CODE_URL) }) {
            Text(text = stringResource(R.string.source_code))
        }
    }
}

const val PRIVACY_POLICY_URL = "https://github.com/sunit1999/News/blob/main/PRIVACY_POLICY.md"
const val SOURCE_CODE_URL = "https://github.com/sunit1999/News"
const val CONTACT_US_URL = "https://forms.gle/RGaV7qiduu9FHEjW9"
