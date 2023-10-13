package com.sunit.news.data

import com.sunit.news.feature.home.models.UiArticle

interface SearchRepository {
    suspend fun searchEverything(query: String): List<UiArticle>
}
