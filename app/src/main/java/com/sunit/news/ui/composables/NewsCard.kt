package com.sunit.news.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sunit.news.R
import com.sunit.news.feature.home.models.UiArticle
import com.sunit.news.util.toHumanReadableDate
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsCard(
    article: UiArticle,
    onClick: () -> Unit,
    onToggleBookmark: (id: UUID, isBookmarked: Boolean) -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(article.urlToImage)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                fallback = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            )
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                ) {
                    Text(
                        text = article.title,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = {
                        onToggleBookmark(article.id, !article.isBookmarked)
                    }) {
                        Icon(
                            imageVector = if (article.isBookmarked)
                                Icons.Outlined.Bookmark
                            else
                                Icons.Outlined.BookmarkBorder,
                            contentDescription = null
                        )
                    }
                }
                Row {
                    Text(
                        text = "${article.publishedAt.toHumanReadableDate()} | ${article.author} for ${article.sourceName}",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                Text(
                    text = article.description,
                    style = MaterialTheme.typography.bodyLarge
                )
                LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    items(10) {
                        SuggestionChip(
                            onClick = { /*TODO*/ },
                            label = {
                                Text(
                                    text = "Chip $it",
                                    style = MaterialTheme.typography.labelLarge,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                            },
                        )
                    }
                }
            }
        }
    }
}
