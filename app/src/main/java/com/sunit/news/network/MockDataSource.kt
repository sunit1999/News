package com.sunit.news.network

import android.content.Context
import com.google.gson.Gson
import com.sunit.news.network.models.HeadlinesResponse
import com.sunit.news.util.readJsonDataFromAsset
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockDataSource @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    @ApplicationContext private val context: Context,
    private val gson: Gson
) : NetworkDataSource {
    override suspend fun getTopHeadlines(countryCode: String): HeadlinesResponse {
        return withContext(ioDispatcher) {
            context.readJsonDataFromAsset("top-headlines.json", gson)
        }
    }

    override suspend fun searchEverything(
        query: String,
        searchIn: String?,
        from: String?,
        to: String?,
        language: String?,
        sortBy: String?
    ): HeadlinesResponse {
        return withContext(ioDispatcher) {
            context.readJsonDataFromAsset("top-headlines.json", gson)
        }
    }
}
