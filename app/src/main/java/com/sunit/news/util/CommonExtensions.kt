package com.sunit.news.util

import android.content.Context
import com.google.gson.Gson
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toJavaInstant
import kotlinx.datetime.toLocalDateTime
import java.io.InputStream
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Date
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

fun getCurrentDate(): String {
    val currentDateTime = Clock.System.now()
    val formattedDate: LocalDate = currentDateTime.toLocalDateTime(TimeZone.currentSystemDefault())
        .date

    return formattedDate.toString()
}

fun milliSecondsToDateString(utcMillis: Long): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = Date(utcMillis)
    return sdf.format(date)
}
