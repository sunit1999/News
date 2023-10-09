package com.sunit.news.feature.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunit.news.data.NewsRepository
import com.sunit.news.feature.home.models.NewsFeedUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val bookmarksFeedUiState = newsRepository.observeBookmarkedHeadlines()
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
}
