package com.sunit.news.data

import com.sunit.news.feature.home.models.UiArticle
import com.sunit.news.sync.Syncable
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface NewsRepository : Syncable {
    fun getTopHeadlines(): Flow<List<UiArticle>>
    suspend fun toggleBookmarkById(id: UUID, isBookmarked: Boolean)
}
