package com.sunit.news.network.di

import com.sunit.news.BuildConfig
import com.sunit.news.network.NetworkDataSource
import com.sunit.news.network.RetrofitApi
import com.sunit.news.network.RetrofitDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpCallFactory(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): Call.Factory {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("X-Api-Key", BuildConfig.API_KEY)
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }


    @Provides
    @Singleton
    fun provideNetworkApi(
        gsonConverterFactory: GsonConverterFactory,
        okHttpCallFactory: Call.Factory
    ): RetrofitApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .callFactory(okHttpCallFactory)
            .build()
            .create(RetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkDataSource(
        retrofitApi: RetrofitApi
    ): NetworkDataSource {
        return RetrofitDataSource(retrofitApi)
    }
}
