package com.sunit.news.feature.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunit.news.data.SearchRepository
import com.sunit.news.feature.home.models.NewsFeedUiState
import com.sunit.news.feature.search.models.SearchIn
import com.sunit.news.feature.search.models.SortBy
import com.sunit.news.util.getCurrentDate
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

    var searchIn = mutableStateListOf(*SearchIn.values())
        private set

    var from = MutableStateFlow(getCurrentDate())
        private set

    var to = MutableStateFlow(getCurrentDate())
        private set

    var sortBy by mutableStateOf(SortBy.PUBLISHED_AT)
        private set

    fun updateSortBy(key: SortBy) {
        sortBy = key
    }

    fun updateFrom(newFrom: String) {
        from.value = newFrom
    }

    fun updateTo(newTo: String) {
        to.value = newTo
    }

    fun updateSearchIn(entry: SearchIn) {
        if (searchIn.contains(entry)) searchIn.remove(entry)
        else searchIn.add(entry)
    }

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

    fun searchEverythingWithFilters() {
        viewModelScope.launch {
            searchUiState.value = NewsFeedUiState.Loading
            val results = searchRepository.searchEverything(
                query = searchQuery.value,
                searchIn = searchIn.joinToString(separator = ",") {
                    it.key
                },
                from = from.value,
                to = to.value,
                sortBy = sortBy.key
            )
            searchUiState.value = NewsFeedUiState.Success(results)
        }
    }
}
