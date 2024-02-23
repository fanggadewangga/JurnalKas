package com.ptotakkanan.jurnalkas.domain

data class WalletDetail(
    val wallet: Wallet,
    val income: Long = 0,
    val outcome: Long = 0,
    val transactions: List<Transaction> = emptyList()
)
