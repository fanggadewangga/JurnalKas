package com.ptotakkanan.jurnalkas.data.model

import com.ptotakkanan.jurnalkas.domain.Transaction

data class Analysis(
    val balance: Long,
    val transactions: List<Transaction>
) {
    fun toDomain() = Analysis(balance, transactions)
}
