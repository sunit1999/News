package com.sunit.news.database.di

import android.content.Context
import androidx.room.Room
import com.sunit.news.database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesNiaDatabase(
        @ApplicationContext context: Context,
    ): NewsDatabase = Room.databaseBuilder(
        context,
        NewsDatabase::class.java,
        "news_database",
    ).fallbackToDestructiveMigration().build()
}
