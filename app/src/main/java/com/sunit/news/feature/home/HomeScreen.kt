package com.sunit.news.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sunit.news.ui.composables.NewsFeed

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val feedUiState by homeViewModel.feedUiState.collectAsStateWithLifecycle()

    NewsFeed(
        feedUiState = feedUiState,
        onToggleBookmark = homeViewModel::toggleBookmarkById,
        modifier = modifier
    )
}
