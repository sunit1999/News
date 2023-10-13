package com.sunit.news.network

import com.sunit.news.network.models.HeadlinesResponse

interface NetworkDataSource {
    suspend fun getTopHeadlines(countryCode: String): HeadlinesResponse
    suspend fun searchEverything(query: String): HeadlinesResponse
}
