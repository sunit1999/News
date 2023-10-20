package com.sunit.news.data.repository

import com.sunit.news.data.SearchRepository
import com.sunit.news.database.models.ArticleEntity
import com.sunit.news.feature.home.models.UiArticle
import com.sunit.news.network.NetworkDataSource
import com.sunit.news.network.models.NetworkArticle
import com.sunit.news.util.toArticleEntity
import com.sunit.news.util.toUiArticle
import timber.log.Timber
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val network: NetworkDataSource
) : SearchRepository {
    override suspend fun searchEverything(
        query: String,
        searchIn: String?,
        from: String?,
        to: String?,
        language: String?,
        sortBy: String?,
    ): List<UiArticle> {
        return try {
            val searchResult = network.searchEverything(
                query = query,
                searchIn = searchIn,
                from = from,
                to = to,
                language = language,
                sortBy = sortBy
            ).articles
                .map(NetworkArticle::toArticleEntity)
                .map(ArticleEntity::toUiArticle)
            searchResult
        } catch (e: Exception) {
            Timber.e("Failed to search for $query. ${e.message}")
            emptyList()
        }
    }
}
