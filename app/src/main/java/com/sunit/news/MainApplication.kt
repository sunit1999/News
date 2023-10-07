package com.sunit.news

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.sunit.news.sync.Sync
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface HiltWorkerFactoryEntryPoint {
    fun hiltWorkerFactory(): HiltWorkerFactory
}

@HiltAndroidApp
class MainApplication : Application(), Configuration.Provider {

    override val workManagerConfiguration = Configuration.Builder().setWorkerFactory(
        EntryPoints.get(this, HiltWorkerFactoryEntryPoint::class.java).hiltWorkerFactory()
    ).build()

    override fun onCreate() {
        super.onCreate()
        // Initialize Work Manager
        Sync.initialize(context = this)
    }
}
