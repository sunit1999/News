package com.sunit.news.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
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
    onToggleBookmark: (UUID, Boolean) -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(article.urlToImage)
                    .crossfade(true)
                    .build(),
                loading = {
                    Image(
                        painter = painterResource(id = R.drawable.news_placeholder),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                },
                error = {
                    Image(
                        painter = painterResource(id = R.drawable.news_placeholder),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                },
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
                            contentDescription = if (article.isBookmarked)
                                stringResource(R.string.remove_from_bookmarks)
                            else
                                stringResource(R.string.add_to_bookmarks)
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
            }
        }
    }
}
