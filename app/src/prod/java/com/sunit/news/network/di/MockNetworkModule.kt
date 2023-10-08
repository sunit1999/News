package com.sunit.news.network.di

import com.sunit.news.network.NetworkDataSource
import com.sunit.news.network.RetrofitDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ProdNetworkModule {

    @Binds
    fun RetrofitDataSource.binds(): NetworkDataSource
}
