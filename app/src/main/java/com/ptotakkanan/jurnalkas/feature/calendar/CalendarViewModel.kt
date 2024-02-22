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

    private val _dummyTransaction = listOf(
        Transaction(
            "Jajan",
            "Seblak",
            R.drawable.ic_chocolate,
            nominal = 25000,
            isIncome = false,
            date = "9 Januari 2024"
        ),
        Transaction(
            "Transportasi",
            "Bensin",
            R.drawable.ic_traffic,
            nominal = 36000,
            isIncome = false,
            date = "9 Januari 2024"
        ),
        Transaction(
            "Makan",
            "Mie Ayam",
            R.drawable.ic_food,
            nominal = 15000,
            isIncome = false,
            date = "3 Januari 2024"
        ),
        Transaction(
            "Gaji",
            "Gaji Bulanan",
            R.drawable.ic_money,
            nominal = 10000000,
            isIncome = true,
            date = "1 Januari 2024"
        ),
        Transaction(
            "Nonton",
            "Nonton Spy X",
            R.drawable.ic_ticker,
            nominal = 45000,
            isIncome = false,
            date = "1 Januari 2024"
        ),
        Transaction(
            "Pulsa",
            "Pulsa untuk paketan",
            R.drawable.ic_communication,
            nominal = 40000,
            isIncome = false,
            date = "2 Januari 2024"
        ),
    ).toMutableStateList()

    val dummyTransaction: List<Transaction>
        get() = _dummyTransaction

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