package com.sunit.news.data

import com.sunit.news.database.ArticleDao
import com.sunit.news.database.models.ArticleEntity
import com.sunit.news.feature.home.models.UiArticle
import com.sunit.news.network.NetworkDataSource
import com.sunit.news.network.models.NetworkArticle
import com.sunit.news.util.toArticleEntity
import com.sunit.news.util.toUiArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject

class OfflineFirstNewsRepository @Inject constructor(
    private val network: NetworkDataSource,
    private val articleDao: ArticleDao,
) : NewsRepository {
    override fun getTopHeadlines(): Flow<List<UiArticle>> {
        return articleDao.getAllArticles().map {
            it.map(ArticleEntity::toUiArticle)
        }
    }

    override suspend fun toggleBookmarkById(id: UUID, isBookmarked: Boolean) {
        try {
            articleDao.toggleBookmarkById(id, isBookmarked)
        } catch (e: Exception) {
            Timber.e("Failed to update bookmark ${e.message}")
        }
    }

    override suspend fun sync(): Boolean {
        return try {
            val articleEntities = network.getTopHeadlines().articles
                .map(NetworkArticle::toArticleEntity)

            articleDao.insertOrIgnoreArticles(articleEntities)

            true
        } catch (e: Exception) {
            Timber.e("Failed to Sync NewsRepository ${e.message}")
            false
        }
    }
}
