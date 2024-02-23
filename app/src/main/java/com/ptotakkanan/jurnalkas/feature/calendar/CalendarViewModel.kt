package com.ptotakkanan.jurnalkas.feature.calendar

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.domain.Transaction
import com.ptotakkanan.jurnalkas.feature.input.InputState
import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption

class CalendarViewModel: ViewModel() {

    private val _tabOptions = listOf(
        SelectionOption("Kalender", true),
        SelectionOption("Detail", false),
    ).toMutableStateList()

    val tabOptions: List<SelectionOption<String>>
        get() = _tabOptions

    private val _state = mutableStateOf(CalendarState())
    val state = _state

    fun onEvent(event: CalendarEvent) {
        when(event) {
            is CalendarEvent.SwitchTab -> {
                _tabOptions.forEach { it.selected = false }
                _tabOptions.find { it.option == event.value.option }?.selected = true
                _state.value = _state.value.copy(selectedTab = event.value.option)
            }
        }
    }
}