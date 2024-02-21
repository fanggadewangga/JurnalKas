package com.ptotakkanan.jurnalkas.feature.calendar

import com.ptotakkanan.jurnalkas.domain.Category
import com.ptotakkanan.jurnalkas.feature.category.CategoryEvent
import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption

sealed class CalendarEvent {
    data class SelectCategory(val selectionOption: SelectionOption<String>): CalendarEvent()
}