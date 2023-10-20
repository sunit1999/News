package com.sunit.news.feature.search.models

enum class SearchIn(val key: String) {
    TITLE("title"),
    DESCRIPTION("description"),
    CONTENT("content")
}

enum class SortBy(val key: String) {
    RELEVANCY("relevancy"),
    POPULARITY("popularity"),
    PUBLISHED_AT("publishedAt")
}
