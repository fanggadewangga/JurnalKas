package com.ptotakkanan.jurnalkas.domain

import androidx.annotation.DrawableRes

data class Wallet(
    val walletId: String = "",
    val name: String = "",
    val icon: String = "",
    val balance: Long = 0L,
)
