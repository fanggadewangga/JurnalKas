package com.ptotakkanan.jurnalkas.domain

import androidx.annotation.DrawableRes

data class Transaction(
    val title: String,
    val description: String,
    @DrawableRes val icon: Int,
    val nominal: Long,
    val isIncome: Boolean,
    val date: String,
)
