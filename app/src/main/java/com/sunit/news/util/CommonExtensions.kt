package com.sunit.news.util

import kotlinx.datetime.toInstant
import kotlinx.datetime.toJavaInstant
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
