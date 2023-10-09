package com.sunit.news.feature.bookmarks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sunit.news.feature.home.models.NewsFeedUiState
import com.sunit.news.ui.composables.NewsCard
import com.sunit.news.ui.composables.NewsLoadingIndicator

@Composable
fun BookmarksScreen(
    bookmarksViewModel: BookmarksViewModel = hiltViewModel()
) {
    val bookmarksUiState by bookmarksViewModel.bookmarksUiState.collectAsStateWithLifecycle()

    when (bookmarksUiState) {
        is NewsFeedUiState.Loading -> {
            NewsLoadingIndicator()
        }

        is NewsFeedUiState.Error -> {
            Text(text = "Error Loading feed")
        }

        is NewsFeedUiState.Success -> {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                items((bookmarksUiState as NewsFeedUiState.Success).feed) { uiArticle ->
                    NewsCard(
                        article = uiArticle,
                        onToggleBookmark = bookmarksViewModel::toggleBookmarkById
                    )
                }
            }
        }
    }
}
