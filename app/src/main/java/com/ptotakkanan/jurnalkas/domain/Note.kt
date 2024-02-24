package com.ptotakkanan.jurnalkas.domain

import androidx.annotation.DrawableRes

data class Note(
    val title: String,
    val imageUrl: String,
    val nominal: Long,
    val isIncome: Boolean,
)
