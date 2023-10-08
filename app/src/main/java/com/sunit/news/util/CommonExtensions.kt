package com.sunit.news.util

import android.content.Context
import com.google.gson.Gson
import kotlinx.datetime.toInstant
import kotlinx.datetime.toJavaInstant
import java.io.InputStream
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun String.toHumanReadableDate(): String {
    val zoneId = ZoneId.systemDefault()

    val publishedDate = this.toInstant()

    return DateTimeFormatter
        .ofLocalizedDate(FormatStyle.MEDIUM)
        .withLocale(Locale.getDefault())
        .withZone(zoneId)
        .format(publishedDate.toJavaInstant())
}

inline fun <reified T> Context.readJsonDataFromAsset(fileName: String, gson: Gson): T {
    return try {
        val inputStream: InputStream = assets.open(fileName)
        val json = inputStream.bufferedReader().use { it.readText() }
        gson.fromJson(json, T::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
        error("Error Parsing file $fileName")
    }
}
