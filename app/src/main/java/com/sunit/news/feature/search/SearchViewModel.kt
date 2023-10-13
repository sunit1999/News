package com.sunit.news.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunit.news.data.SearchRepository
import com.sunit.news.feature.search.models.SearchUiState
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

    var searchUiState = MutableStateFlow<SearchUiState>(SearchUiState.Success(emptyList()))
        private set

    fun onSearchQueryChange(q: String) {
        searchQuery.value = q
    }

    fun searchEverything() {
        viewModelScope.launch {
            searchUiState.value = SearchUiState.Loading
            val results = searchRepository.searchEverything(searchQuery.value)
            searchUiState.value = SearchUiState.Success(results)
        }
    }
}
