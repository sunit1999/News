package com.sunit.news.data

import com.sunit.news.database.ArticleDao
import com.sunit.news.database.models.ArticleEntity
import com.sunit.news.database.models.toArticle
import com.sunit.news.network.NetworkDataSource
import com.sunit.news.network.models.Article
import com.sunit.news.network.models.toArticleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineFirstNewsRepository @Inject constructor(
    private val network: NetworkDataSource,
    private val articleDao: ArticleDao,
) : NewsRepository {
    override fun getTopHeadlines(): Flow<List<Article>> {
        return articleDao.getAllArticles().map {
            it.map(ArticleEntity::toArticle)
        }
    }

    override suspend fun sync(): Boolean {
        return try {
            val articleEntities = network.getTopHeadlines().articles
                .map(Article::toArticleEntity)

            articleDao.insertOrIgnoreArticles(articleEntities)

            true
        } catch (e: Exception) {
            println(e.message)
            false
        }
    }
}
