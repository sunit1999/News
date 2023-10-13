package com.sunit.news.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sunit.news.feature.search.models.SearchUiState
import com.sunit.news.ui.composables.NewsFeedSuccess
import com.sunit.news.ui.composables.NewsLoadingIndicator

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by searchViewModel.searchQuery.collectAsStateWithLifecycle()
    val searchUiState by searchViewModel.searchUiState.collectAsStateWithLifecycle()
    Column {

        when (searchUiState) {
            is SearchUiState.Loading -> {
                NewsLoadingIndicator()
            }

            is SearchUiState.Success -> {
                SearchView(
                    state = searchQuery,
                    onValueChange = searchViewModel::onSearchQueryChange,
                    onDone = searchViewModel::searchEverything,
                    modifier = modifier.padding(bottom = 16.dp)
                )
                NewsFeedSuccess(
                    feed = (searchUiState as SearchUiState.Success).searchResult,
                    onToggleBookmark = { _, _ ->
                        // todo
                    },
                    modifier = modifier
                )
            }
        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    state: String,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = state,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                onDone()
            }
        ),
        modifier = modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (state.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onValueChange("")
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = null
                    )
                }
            }
        },
        singleLine = true
    )
}
