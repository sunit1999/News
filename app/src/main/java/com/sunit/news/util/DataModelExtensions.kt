package com.sunit.news.util

import com.sunit.news.database.models.ArticleEntity
import com.sunit.news.feature.home.models.UiArticle
import com.sunit.news.network.models.NetworkArticle
import com.sunit.news.network.models.Source
import java.util.UUID

fun NetworkArticle.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        sourceId = this.source.id,
        sourceName = this.source.name,
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage,
        isBookmarked = false,
    )
}

fun NetworkArticle.toUiArticle(): UiArticle {
    return UiArticle(
        id = UUID.randomUUID(),
        author = author,
        description = description,
        publishedAt = publishedAt,
        sourceName = source.name,
        title = title,
        url = url,
        urlToImage = urlToImage,
        isBookmarked = false,
    )
}

fun ArticleEntity.toNetworkArticle(): NetworkArticle {
    return NetworkArticle(
        author = author ?: "Unknown",
        content = content ?: "Unknown",
        description = description ?: "Unknown",
        publishedAt = publishedAt,
        source = Source(id = sourceId, name = sourceName ?: "Unknown"),
        title = title ?: "Unknown",
        url = url,
        urlToImage = urlToImage
    )
}

fun ArticleEntity.toUiArticle(): UiArticle {
    return UiArticle(
        id = id,
        author = author ?: "Unknown",
        description = description ?: "Unknown",
        publishedAt = publishedAt,
        sourceName = sourceName ?: "Unknown",
        title = title ?: "Unknown",
        url = url,
        urlToImage = urlToImage,
        isBookmarked = isBookmarked,
    )
}
