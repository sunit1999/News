package com.sunit.news.ui.composables

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sunit.news.feature.home.models.NewsFeedUiState
import com.sunit.news.feature.home.models.UiArticle
import com.sunit.news.util.launchCustomChromeTab
import kotlinx.coroutines.launch
import java.util.UUID

@Composable
fun NewsFeed(
    feedUiState: NewsFeedUiState,
    onToggleBookmark: (UUID, Boolean) -> Unit,
    modifier: Modifier = Modifier,
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
                feed = feedUiState.feed,
                onToggleBookmark = onToggleBookmark,
                modifier = modifier
            )
        }
    }
}

@Composable
fun NewsFeedSuccess(
    feed: List<UiArticle>,
    onToggleBookmark: (UUID, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (feed.isEmpty()) {
        EmptyFeed(modifier)
    }

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val showGoToTopButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
    ) {
        items(
            items = feed,
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

    AnimatedVisibility(
        visible = showGoToTopButton,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        ScrollToTopButton(
            modifier = modifier,
            onClick = {
                scope.launch {
                    listState.animateScrollToItem(index = 0)
                }
            }
        )
    }
}
