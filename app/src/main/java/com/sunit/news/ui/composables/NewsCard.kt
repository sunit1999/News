package com.sunit.news.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sunit.news.R
import com.sunit.news.network.models.Article
import com.sunit.news.util.toHumanReadableDate

@Composable
fun NewsCard(
    article: Article
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
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
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Outlined.BookmarkBorder, contentDescription = null)
                    }
                }
                Row {
                    Text(
                        text = "${article.publishedAt.toHumanReadableDate()} | ${article.author} for ${article.source.name}",
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
