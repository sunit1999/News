package com.sunit.news.data.di

import com.sunit.news.data.NewsRepository
import com.sunit.news.data.OfflineFirstNewsRepository
import com.sunit.news.database.ArticleDao
import com.sunit.news.network.NetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideNewsRepository(
        networkDataSource: NetworkDataSource,
        articleDao: ArticleDao,
    ): NewsRepository {
        return OfflineFirstNewsRepository(networkDataSource, articleDao)
    }
}
