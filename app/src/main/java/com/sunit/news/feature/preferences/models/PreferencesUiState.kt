package com.sunit.news.feature.preferences.models

sealed interface PreferencesUiState {
    data object Loading : PreferencesUiState
    data class Success(val editablePreferences: EditablePreferences) : PreferencesUiState
}
