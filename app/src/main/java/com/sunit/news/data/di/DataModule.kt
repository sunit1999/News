package com.sunit.news.data.di

import com.sunit.news.data.NewsRepository
import com.sunit.news.data.SearchRepository
import com.sunit.news.data.UserDataRepository
import com.sunit.news.data.repository.OfflineFirstNewsRepository
import com.sunit.news.data.repository.OfflineFirstUserDataRepository
import com.sunit.news.data.repository.SearchRepositoryImpl
import com.sunit.news.database.ArticleDao
import com.sunit.news.datastore.UserPreferencesDataSource
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
        preferencesDataSource: UserPreferencesDataSource
    ): NewsRepository {
        return OfflineFirstNewsRepository(networkDataSource, articleDao, preferencesDataSource)
    }

    @Provides
    @Singleton
    fun provideUserDataRepository(
        userPreferencesDataSource: UserPreferencesDataSource
    ): UserDataRepository {
        return OfflineFirstUserDataRepository(userPreferencesDataSource)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(
        networkDataSource: NetworkDataSource
    ): SearchRepository {
        return SearchRepositoryImpl(networkDataSource)
    }
}
