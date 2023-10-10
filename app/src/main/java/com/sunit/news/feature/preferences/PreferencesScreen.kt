package com.sunit.news.feature.preferences

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sunit.news.datastore.models.DarkThemeConfig
import timber.log.Timber

@Composable
fun PreferencesScreen(
    preferencesViewModel: PreferencesViewModel = hiltViewModel()
) {
    val preferencesUiState by preferencesViewModel.preferencesUiState.collectAsStateWithLifecycle()

    Timber.i(preferencesUiState.toString())
    Column {
        Button(onClick = {
            preferencesViewModel.updateDarkThemeConfig(DarkThemeConfig.LIGHT)
        }) {
            Text(text = "On Preferences screen Light")
        }

        Button(onClick = {
            preferencesViewModel.updateDarkThemeConfig(DarkThemeConfig.DARK)
        }) {
            Text(text = "On Preferences screen DARK")
        }

        Button(onClick = {
            preferencesViewModel.updateDarkThemeConfig(DarkThemeConfig.SYSTEM_DEFAULT)
        }) {
            Text(text = "On Preferences screen Default")
        }
    }
}
