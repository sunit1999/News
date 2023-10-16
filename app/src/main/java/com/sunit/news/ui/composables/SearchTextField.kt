package com.sunit.news.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.sunit.news.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextField(
    state: String,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = state,
        onValueChange = onValueChange,
        placeholder = {
            Text("Search anything")
        },
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
                        contentDescription = stringResource(R.string.clear_input)
                    )
                }
            }
        },
        singleLine = true
    )
}
