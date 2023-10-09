package com.sunit.news.feature.bookmarks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sunit.news.ui.composables.NewsFeed

@Composable
fun BookmarksScreen(
    bookmarksViewModel: BookmarksViewModel = hiltViewModel()
) {
    val bookmarksFeedUiState by bookmarksViewModel.bookmarksFeedUiState.collectAsStateWithLifecycle()

    NewsFeed(
        feedUiState = bookmarksFeedUiState,
        onToggleBookmark = bookmarksViewModel::toggleBookmarkById
    )
}
