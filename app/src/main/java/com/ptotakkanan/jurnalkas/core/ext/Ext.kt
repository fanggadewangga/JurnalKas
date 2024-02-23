package com.ptotakkanan.jurnalkas.core.ext

import com.ptotakkanan.jurnalkas.feature.util.date.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
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