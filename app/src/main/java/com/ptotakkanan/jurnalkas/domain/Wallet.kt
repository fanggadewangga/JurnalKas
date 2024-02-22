package com.ptotakkanan.jurnalkas.domain

import androidx.annotation.DrawableRes

data class Wallet(
    val name: String,
    @DrawableRes val icon: Int,
)
