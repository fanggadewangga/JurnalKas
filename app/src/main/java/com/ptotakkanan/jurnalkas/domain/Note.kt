package com.ptotakkanan.jurnalkas.domain

import androidx.annotation.DrawableRes

data class Note(
    val title: String,
    @DrawableRes val icon: Int,
    val nominal: Long,
    val isIncome: Boolean = false,
    val isOutcome: Boolean = false,
)
