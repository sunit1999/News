package com.sunit.news.feature.home

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

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val feedUiState by homeViewModel.feedUiState.collectAsStateWithLifecycle()

    when (feedUiState) {
        is NewsFeedUiState.Loading -> {
            Text(text = "Loading feed")
        }

        is NewsFeedUiState.Error -> {
            Text(text = "Error Loading feed")
        }

        is NewsFeedUiState.Success -> {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                items((feedUiState as NewsFeedUiState.Success).feed) { article ->
                    NewsCard(article = article)
                }
            }
        }
    }
}
