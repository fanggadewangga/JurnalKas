package com.ptotakkanan.jurnalkas.domain

import androidx.annotation.DrawableRes

data class CalendarTransaction(
    val month: String,
    val isSuccess: Boolean,
    val nominal: Long,
    val date: String,
)
