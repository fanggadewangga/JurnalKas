package com.ptotakkanan.jurnalkas.feature.analysis

import com.ptotakkanan.jurnalkas.domain.Transaction

data class AnalysisState(
    val isLoading: Boolean = false,
    val balance: Long = 0,
    val thisMonthIncome: Long = 0,
    val thisMonthOutcome: Long = 0,
    val outcomeTransactions: List<Transaction> = emptyList(),
    val incomeTransactions: List<Transaction> = emptyList(),
    val selectedTab: String = "Pemasukan",
)
