package com.ptotakkanan.jurnalkas.core.ext

import android.os.Build
import androidx.annotation.RequiresApi
import com.ptotakkanan.jurnalkas.feature.util.date.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Long.toCurrency(locale: Locale = Locale("id", "ID")) = run {
    val numberFormat = NumberFormat.getCurrencyInstance(locale)
    numberFormat.minimumFractionDigits = 0
    numberFormat.format(this).toString()
}

fun String.convertDateFormat(fromFormat: DateFormat, toFormat: DateFormat): String {
    val originalFormatter = SimpleDateFormat(fromFormat.format, Locale("id", "ID"))
    val targetFormatter = SimpleDateFormat(toFormat.format, Locale("id", "ID"))

    val date = originalFormatter.parse(this)
    return targetFormatter.format(date!!)
}

fun createTimeStamp(format: DateFormat): String = run {
    val date = java.util.Date()
    val formatter = SimpleDateFormat(format.format, Locale("id", "ID"))
    formatter.timeZone = java.util.TimeZone.getTimeZone("Asia/Jakarta")
    formatter.format(date)
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.toMillis() = this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

@RequiresApi(Build.VERSION_CODES.O)
fun Long.toFormattedDateString(pattern: String): String {
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale("id", "ID"))
    val date = LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault())
    return date.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertDateToMillis(date: String): Long {
    val formatter = DateTimeFormatter.ofPattern(DateFormat.DATE.format, Locale("id", "ID"))
    val localDate = LocalDate.parse(date, formatter)
    return localDate.atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000
}