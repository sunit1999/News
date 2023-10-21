package com.sunit.news.sync

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.sunit.news.R

const val SYNC_NOTIFICATION_ID = 0
const val SYNC_NOTIFICATION_CHANNEL_ID = "SYNC_NOTIFICATION_CHANNEL_ID"

fun Context.syncWorkNotification(): Notification {
    val channel = NotificationChannel(
        SYNC_NOTIFICATION_CHANNEL_ID,
        getString(R.string.sync),
        NotificationManager.IMPORTANCE_DEFAULT
    ).apply {
        description = getString(R.string.background_tasks_for_news_app)
    }

    // Register the channel with the system.
    val notificationManager: NotificationManager =
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    notificationManager.createNotificationChannel(channel)

    return NotificationCompat.Builder(this, SYNC_NOTIFICATION_CHANNEL_ID)
        .setSmallIcon(R.drawable.news_placeholder)
        .setContentTitle(getString(R.string.sync))
        .setContentText(getString(R.string.app_name))
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .build()
}
