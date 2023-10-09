package com.sunit.news.util

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

fun launchCustomChromeTab(context: Context, uri: Uri) {
    val customTabsIntent = CustomTabsIntent.Builder()
        .build()

    customTabsIntent.launchUrl(context, uri)
}
