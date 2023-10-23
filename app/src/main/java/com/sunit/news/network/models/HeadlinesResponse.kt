package com.sunit.news.network.models

import androidx.annotation.Keep

@Keep
data class HeadlinesResponse(
    val articles: List<NetworkArticle>,
    val status: String,
    val totalResults: Int
)

@Keep
data class NetworkArticle(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String?
)

@Keep
data class Source(
    val id: String?,
    val name: String
)
