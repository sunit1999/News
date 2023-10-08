package com.sunit.news.network.di

import com.sunit.news.network.MockDataSource
import com.sunit.news.network.NetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MockNetworkModule {

    @Binds
    fun MockDataSource.binds(): NetworkDataSource
}
