package com.ptotakkanan.jurnalkas.domain

data class Transaction(
    val transactionId: String = "",
    val walletId: String = "",
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val nominal: Long = 0L,
    val isIncome: Boolean = false,
    val date: String = "",
)
