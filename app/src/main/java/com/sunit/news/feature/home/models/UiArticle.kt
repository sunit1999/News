package com.sunit.news.feature.home.models

import java.util.UUID

data class UiArticle(
    val id: UUID,
    val author: String,
    val description: String,
    val publishedAt: String,
    val sourceName: String,
    val title: String,
    val url: String,
    val urlToImage: String?,
    val isBookmarked: Boolean,
)
