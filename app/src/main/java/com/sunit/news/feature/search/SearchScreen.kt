package com.sunit.news.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sunit.news.ui.composables.NewsFeed
import com.sunit.news.ui.composables.SearchTextField

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by searchViewModel.searchQuery.collectAsStateWithLifecycle()
    val searchUiState by searchViewModel.searchUiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchTextField(
            state = searchQuery,
            onValueChange = searchViewModel::onSearchQueryChange,
            onDone = searchViewModel::searchEverything,
            modifier = modifier.padding(bottom = 16.dp)
        )
        NewsFeed(
            feedUiState = searchUiState,
            onToggleBookmark = { _, _ ->
                // todo
            },
            modifier = modifier
        )
    }
}
