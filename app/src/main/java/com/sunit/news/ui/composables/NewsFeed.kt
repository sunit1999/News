package com.sunit.news.ui.composables

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sunit.news.feature.home.models.NewsFeedUiState
import com.sunit.news.util.launchCustomChromeTab
import java.util.UUID

@Composable
fun NewsFeed(
    feedUiState: NewsFeedUiState,
    onToggleBookmark: (id: UUID, isBookmarked: Boolean) -> Unit
) {

    when (feedUiState) {
        is NewsFeedUiState.Loading -> {
            NewsLoadingIndicator()
        }

        is NewsFeedUiState.Error -> {
            Text(text = "Error Loading feed")
        }

        is NewsFeedUiState.Success -> {
            NewsFeedSuccess(
                feedUiState = feedUiState,
                onToggleBookmark = onToggleBookmark
            )
        }
    }
}

@Composable
fun NewsFeedSuccess(
    feedUiState: NewsFeedUiState,
    onToggleBookmark: (id: UUID, isBookmarked: Boolean) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        items(
            items = (feedUiState as NewsFeedUiState.Success).feed,
            key = { it.id }
        ) { uiArticle ->
            val context = LocalContext.current
            val resourceUrl by remember {
                mutableStateOf(Uri.parse(uiArticle.url))
            }
            NewsCard(
                article = uiArticle,
                onClick = {
                    launchCustomChromeTab(context, resourceUrl)
                },
                onToggleBookmark = onToggleBookmark
            )
        }
    }
}
