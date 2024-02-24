package com.ptotakkanan.jurnalkas.data.model

import com.ptotakkanan.jurnalkas.domain.Transaction

data class Transaction(
    val transactionId: String = "",
    val walletId: String = "",
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val nominal: Long = 0L,
    val isIncome: Boolean = false,
    val date: String = "",
) {
    fun toDomain() = Transaction(transactionId, walletId, title, description, imageUrl, nominal, isIncome, date)
}
