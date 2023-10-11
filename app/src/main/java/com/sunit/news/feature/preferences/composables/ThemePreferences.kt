package com.sunit.news.feature.preferences.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sunit.news.datastore.models.DarkThemeConfig
import com.sunit.news.feature.preferences.PreferencesViewModel
import com.sunit.news.feature.preferences.models.PreferencesUiState
import com.sunit.news.ui.composables.NewsLoadingIndicator

const val THEME_PREFERENCES_ROUTE = "theme_preferences"

@Composable
fun ThemePreferences(
    preferencesViewModel: PreferencesViewModel = hiltViewModel(),
) {
    val preferencesUiState by
    preferencesViewModel.preferencesUiState.collectAsStateWithLifecycle()

    when (preferencesUiState) {
        is PreferencesUiState.Loading -> NewsLoadingIndicator()
        is PreferencesUiState.Success -> {
            val editablePreferences =
                (preferencesUiState as PreferencesUiState.Success).editablePreferences

            Column(Modifier.selectableGroup()) {
                DarkThemeConfig.values().forEach {
                    RadioButtonRow(
                        text = stringResource(id = it.displayName),
                        selected = editablePreferences.darkThemeConfig == it,
                        onClick = {
                            preferencesViewModel.updateDarkThemeConfig(it)
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun RadioButtonRow(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .selectable(
                selected = selected,
                onClick = onClick,
                role = Role.RadioButton
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = null
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}
