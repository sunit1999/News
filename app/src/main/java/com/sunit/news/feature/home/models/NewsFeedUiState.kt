package com.sunit.news.feature.home.models

import com.sunit.news.network.models.Article

sealed interface NewsFeedUiState {
    data object Loading : NewsFeedUiState
    data object Error : NewsFeedUiState
    data class Success(
        val feed: List<Article>
    ) : NewsFeedUiState
}
