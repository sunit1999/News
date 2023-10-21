package com.sunit.news.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkerParameters
import com.sunit.news.data.NewsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// All sync work needs an internet connection
val SyncConstraints
    get() = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParams: WorkerParameters,
    private val newsRepository: NewsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        return withContext(dispatcher) {

            val isSuccessful = newsRepository.sync()

            if (isSuccessful)
                Result.success()
            else
                Result.retry()
        }
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return ForegroundInfo(SYNC_NOTIFICATION_ID, context.syncWorkNotification())
    }

    companion object {
        fun buildSyncRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<SyncWorker>()
                .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                .setConstraints(SyncConstraints)
                .build()
        }
    }
}
