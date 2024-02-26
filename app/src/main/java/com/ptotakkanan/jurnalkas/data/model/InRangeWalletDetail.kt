package com.ptotakkanan.jurnalkas.data.model

import com.ptotakkanan.jurnalkas.domain.Transaction
import com.ptotakkanan.jurnalkas.domain.Wallet

data class InRangeWalletDetail(
    val income: Long = 0,
    val outcome: Long = 0,
    val listIncomeTransaction: List<InRangeTransaction>,
    val listOutcomeTransaction: List<InRangeTransaction>,
)