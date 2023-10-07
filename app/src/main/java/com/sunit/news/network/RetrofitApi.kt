package com.sunit.news.network

import com.sunit.news.network.models.HeadlinesResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.coroutines.cancellation.CancellationException

interface RetrofitApi {
    @GET(value = "top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") countryCode: String = "us"
    ): Response<HeadlinesResponse>
}

suspend fun <T> suspendRunCatching(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    block: suspend () -> Response<T>,
): T {
    return withContext(dispatcher) {
        try {
            val response = block()
            if (response.isSuccessful) {
                return@withContext response.body() ?: error("null response")
            }
            val errorBody = response.errorBody()?.string()
            val errorCode = response.code()

            println(errorBody.toString())
            error(errorCode)
        } catch (cancellationException: CancellationException) {
            throw cancellationException
        } catch (exception: Exception) {
            throw exception
        }
    }
}
