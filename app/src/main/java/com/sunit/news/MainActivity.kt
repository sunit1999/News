package com.sunit.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sunit.news.datastore.models.DarkThemeConfig
import com.sunit.news.ui.NewsApp
import com.sunit.news.ui.theme.NewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainActivityUIState by mainActivityViewModel.uiState.collectAsStateWithLifecycle()
            val isOnline by mainActivityViewModel.isOnline.collectAsStateWithLifecycle()

            NewsTheme(
                darkTheme = shouldUseDarkTheme(uiState = mainActivityUIState)
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewsApp(isOnline)
                }
            }
        }
    }
}

@Composable
private fun shouldUseDarkTheme(
    uiState: MainActivityUIState,
): Boolean = when (uiState) {
    is MainActivityUIState.Success -> when (uiState.userData.darkThemeConfig) {
        DarkThemeConfig.LIGHT -> false
        DarkThemeConfig.DARK -> true
        else -> isSystemInDarkTheme()
    }

    else -> isSystemInDarkTheme()
}
