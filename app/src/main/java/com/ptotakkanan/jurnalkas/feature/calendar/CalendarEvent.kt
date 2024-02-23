package com.ptotakkanan.jurnalkas.feature.calendar

import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption

sealed class CalendarEvent {
    data class SwitchTab(val value: SelectionOption<String>): CalendarEvent()
    data object FetchTransactions: CalendarEvent()
    data object FetchBalance: CalendarEvent()
}