package com.sunit.news.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sunit.news.ui.composables.NewsFeed

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val feedUiState by homeViewModel.feedUiState.collectAsStateWithLifecycle()

    NewsFeed(
        feedUiState = feedUiState,
        onToggleBookmark = homeViewModel::toggleBookmarkById
    )
}
