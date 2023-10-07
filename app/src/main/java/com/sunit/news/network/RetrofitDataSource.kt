package com.sunit.news.network

import com.sunit.news.network.models.HeadlinesResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitDataSource @Inject constructor(
    private val api: RetrofitApi
) : NetworkDataSource {
    override suspend fun getTopHeadlines(): HeadlinesResponse {
        return suspendRunCatching {
            api.getTopHeadlines()
        }
    }
}
