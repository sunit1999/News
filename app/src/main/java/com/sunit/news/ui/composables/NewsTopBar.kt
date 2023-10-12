package com.sunit.news.ui.composables

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopBar(text: String) {
    CenterAlignedTopAppBar(
        title = { Text(text = text, fontWeight = FontWeight.Bold) }
    )
}
