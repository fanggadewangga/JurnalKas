package com.ptotakkanan.jurnalkas.core.ext

import java.text.NumberFormat
import java.util.Locale

fun Long.toCurrency(locale: Locale = Locale("id", "ID")) = run {
    val numberFormat = NumberFormat.getCurrencyInstance(locale)
    numberFormat.minimumFractionDigits = 0
    numberFormat.format(this).toString()
}