package com.ptotakkanan.jurnalkas.feature.calendar.detail

import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption

sealed class CalendarDetailEvent {
    data class SelectCategory(val selectionOption: SelectionOption<String>): CalendarDetailEvent()
}