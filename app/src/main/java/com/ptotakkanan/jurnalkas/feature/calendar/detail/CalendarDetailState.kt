package com.ptotakkanan.jurnalkas.feature.calendar.detail

import com.ptotakkanan.jurnalkas.domain.Transaction

data class CalendarDetailState(
    val isLoading: Boolean = false,
    val transactions: List<Transaction> = emptyList()
)
