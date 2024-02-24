package com.ptotakkanan.jurnalkas.domain


data class Analysis(
    val balance: Long,
    val thisMonthNominal: Long,
    val transactions: List<Transaction>
)
