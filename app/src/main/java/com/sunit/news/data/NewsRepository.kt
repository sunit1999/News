package com.sunit.news.data

import com.sunit.news.network.models.Article
import com.sunit.news.sync.Syncable
import kotlinx.coroutines.flow.Flow

interface NewsRepository : Syncable {
    fun getTopHeadlines(): Flow<List<Article>>
}
