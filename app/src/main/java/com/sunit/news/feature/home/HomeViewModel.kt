package com.sunit.news.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunit.news.data.NewsRepository
import com.sunit.news.feature.home.models.NewsFeedUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    val feedUiState = newsRepository.observeTopHeadlines()
        .map(NewsFeedUiState::Success)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NewsFeedUiState.Loading
        )

    fun toggleBookmarkById(id: UUID, isBookmarked: Boolean) {
        viewModelScope.launch {
            newsRepository.toggleBookmarkById(id, isBookmarked)
        }
    }

    fun onRefresh() {
        Timber.i("onRefresh called")
        viewModelScope.launch {
            _isRefreshing.value = true
            newsRepository.sync()
            _isRefreshing.value = false
        }
    }
}
