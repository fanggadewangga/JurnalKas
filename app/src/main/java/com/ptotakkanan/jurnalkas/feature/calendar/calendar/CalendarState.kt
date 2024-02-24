package com.ptotakkanan.jurnalkas.feature.calendar.calendar

import com.ptotakkanan.jurnalkas.domain.Transaction

data class CalendarState(
    val isLoading: Boolean = false,
    val transactions: List<Transaction> = emptyList(),
    val selectedDate: String = "",
    val totalMoney: Long = 0,
    val selectedTab: String = "Kalender"
)