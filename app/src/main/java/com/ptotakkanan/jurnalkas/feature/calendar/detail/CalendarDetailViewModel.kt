package com.ptotakkanan.jurnalkas.feature.calendar.detail

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptotakkanan.jurnalkas.domain.CalendarTransaction
import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CalendarDetailViewModel: ViewModel() {

    private val _options = listOf(
        SelectionOption("Pemasukan", true),
        SelectionOption("Pengeluaran", false)
    ).toMutableStateList()

    val options: List<SelectionOption<String>>
        get() = _options

    private val _dummyIncome = listOf(
        CalendarTransaction(
            month = "Oktober",
            isSuccess = true,
            nominal = 400000,
            date = "01/01/2024"
        ),
        CalendarTransaction(
            month = "November",
            isSuccess = false,
            nominal = 35000,
            date = "03/01/2024"
        ),
        CalendarTransaction(
            month = "November",
            isSuccess = true,
            nominal = 120000,
            date = "04/01/2024"
        ),
        CalendarTransaction(
            month = "September",
            isSuccess = false,
            nominal = 35000,
            date = "12/01/2024"
        )
    ).toMutableStateList()

    val dummyIncome: List<CalendarTransaction>
        get() = _dummyIncome

    private val _dummyOutcome = listOf(
        CalendarTransaction(
            month = "Oktober",
            isSuccess = false,
            nominal = 100000,
            date = "01/01/2024"
        ),
        CalendarTransaction(
            month = "Oktover",
            isSuccess = true,
            nominal = 35000,
            date = "05/01/2024"
        ),
        CalendarTransaction(
            month = "November",
            isSuccess = true,
            nominal = 120000,
            date = "04/01/2024"
        ),
        CalendarTransaction(
            month = "December",
            isSuccess = true,
            nominal = 250000,
            date = "12/01/2024"
        )
    )

    val dummyOutcome: List<CalendarTransaction>
        get() = _dummyOutcome

    private val channel = Channel<UiEvent>()
    val eventFlow = channel.receiveAsFlow()

    fun onEvent(event: CalendarDetailEvent) {
        when(event) {
            is CalendarDetailEvent.SelectCategory -> {
                _options.forEach { it.selected = false }
                _options.find { it.option == event.selectionOption.option }?.selected = true
                viewModelScope.launch {
                    if (_options[0].selected)
                        channel.send(UiEvent.ShowIncome)
                    else
                        channel.send(UiEvent.ShowOutcome)
                }
            }
        }
    }

    sealed class UiEvent {
        data object ShowIncome: UiEvent()
        data object ShowOutcome: UiEvent()
    }
}