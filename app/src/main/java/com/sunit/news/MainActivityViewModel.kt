package com.sunit.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunit.news.data.UserDataRepository
import com.sunit.news.network.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    userDataRepository: UserDataRepository,
    networkMonitor: NetworkMonitor
) : ViewModel() {
    val uiState = userDataRepository.userData
        .map(MainActivityUIState::Success)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MainActivityUIState.Loading
        )

    val isOnline = networkMonitor.isOnline
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = true
        )
}
