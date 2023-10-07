package com.sunit.news.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sunit.news.network.models.Article
import com.sunit.news.network.models.Source

@Entity(
    tableName = "articles"
)
data class ArticleEntity(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val sourceId: String?,
    val sourceName: String?,
    val title: String?,
    @PrimaryKey
    val url: String,
    val urlToImage: String?
)

fun ArticleEntity.toArticle(): Article {
    return Article(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = Source(id = sourceId, name = sourceName),
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}
