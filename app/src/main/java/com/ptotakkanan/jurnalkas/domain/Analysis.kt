package com.ptotakkanan.jurnalkas.domain

import androidx.annotation.DrawableRes

data class Analysis(
    val title: String,
    @DrawableRes val icon: Int,
    val nominal: Long,
    val isIncome: Boolean,
    val date: String,
)
