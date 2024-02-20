package com.ptotakkanan.jurnalkas.domain

import androidx.annotation.DrawableRes

data class Category(
    val category: String,
    @DrawableRes val icon: Int,
)
