package com.sunit.news.data

import com.sunit.news.feature.home.models.UiArticle

interface SearchRepository {
    suspend fun searchEverything(
        query: String,
        searchIn: String? = null,
        from: String? = null,
        to: String? = null,
        language: String? = null,
        sortBy: String? = null,
    ): List<UiArticle>
}
