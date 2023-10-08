package com.sunit.news.feature.home.models

sealed interface NewsFeedUiState {
    data object Loading : NewsFeedUiState
    data object Error : NewsFeedUiState
    data class Success(
        val feed: List<UiArticle>
    ) : NewsFeedUiState
}
