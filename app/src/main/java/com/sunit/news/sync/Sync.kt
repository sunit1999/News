package com.sunit.news.sync

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager

object Sync {
    private const val SYNC_WORKER_NAME = "sync_worker"

    fun initialize(context: Context) {
        WorkManager.getInstance(context).apply {
            enqueueUniqueWork(
                SYNC_WORKER_NAME,
                ExistingWorkPolicy.KEEP,
                SyncWorker.buildSyncRequest()
            )
        }
    }
}
