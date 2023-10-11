package com.sunit.news.feature.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunit.news.data.UserDataRepository
import com.sunit.news.datastore.models.DarkThemeConfig
import com.sunit.news.feature.preferences.models.EditablePreferences
import com.sunit.news.feature.preferences.models.PreferencesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository
) : ViewModel() {
    val preferencesUiState = userDataRepository.userData
        .map { userPreferences ->
            PreferencesUiState.Success(
                EditablePreferences(
                    darkThemeConfig = userPreferences.darkThemeConfig,
                    countryCode = userPreferences.countryCode
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PreferencesUiState.Loading
        )

    fun updateDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        viewModelScope.launch {
            userDataRepository.updateDarkThemeConfig(darkThemeConfig = darkThemeConfig)
        }
    }

    fun updateCountryCode(countryCode: String) {
        viewModelScope.launch {
            userDataRepository.updateCountryCode(countryCode = countryCode)
        }
    }
}
