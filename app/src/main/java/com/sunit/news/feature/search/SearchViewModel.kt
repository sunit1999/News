package com.sunit.news.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunit.news.data.SearchRepository
import com.sunit.news.feature.home.models.NewsFeedUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    var searchQuery = MutableStateFlow("")
        private set

    var searchUiState = MutableStateFlow<NewsFeedUiState>(NewsFeedUiState.Success(emptyList()))
        private set

    fun onSearchQueryChange(q: String) {
        searchQuery.value = q
    }

    fun searchEverything() {
        viewModelScope.launch {
            searchUiState.value = NewsFeedUiState.Loading
            val results = searchRepository.searchEverything(searchQuery.value)
            searchUiState.value = NewsFeedUiState.Success(results)
        }
    }
}
