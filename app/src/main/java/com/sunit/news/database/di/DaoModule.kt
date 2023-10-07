package com.sunit.news.database.di

import com.sunit.news.database.ArticleDao
import com.sunit.news.database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun provideArticleDao(
        database: NewsDatabase
    ): ArticleDao = database.articleDao()
}
