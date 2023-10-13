package com.sunit.news.feature.search.models

import com.sunit.news.feature.home.models.UiArticle

sealed interface SearchUiState {
    data object Loading : SearchUiState
    data class Success(
        val searchResult: List<UiArticle>
    ) : SearchUiState
}
