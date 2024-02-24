package com.ptotakkanan.jurnalkas.domain

import androidx.annotation.DrawableRes

data class Category(
    val categoryId: String,
    val name: String,
    val description: String,
    val example: String,
    val imageUrl: String,
)
