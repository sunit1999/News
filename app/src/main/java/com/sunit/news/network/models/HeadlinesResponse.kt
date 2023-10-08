package com.sunit.news.network.models

data class HeadlinesResponse(
    val articles: List<NetworkArticle>,
    val status: String,
    val totalResults: Int
)

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

data class Source(
    val id: String?,
    val name: String
)
