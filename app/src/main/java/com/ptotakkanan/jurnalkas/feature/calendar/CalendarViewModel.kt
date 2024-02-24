package com.ptotakkanan.jurnalkas.feature.calendar

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptotakkanan.jurnalkas.data.Resource
import com.ptotakkanan.jurnalkas.data.repository.AppRepository
import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CalendarViewModel : ViewModel() {

    private val _tabOptions = listOf(
        SelectionOption("Kalender", true),
        SelectionOption("Detail", false),
    ).toMutableStateList()

    val tabOptions: List<SelectionOption<String>>
        get() = _tabOptions

    private val _state = mutableStateOf(CalendarState())
    val state = _state

    private val repository = AppRepository()

    private val channel = Channel<UiEvent>()
    val eventFlow = channel.receiveAsFlow()

    fun onEvent(event: CalendarEvent) {
        when (event) {
            is CalendarEvent.SwitchTab -> {
                _tabOptions.forEach { it.selected = false }
                _tabOptions.find { it.option == event.value.option }?.selected = true
                _state.value = _state.value.copy(selectedTab = event.value.option)
                Log.d("Selected Tab", event.value.option)
            }

            CalendarEvent.FetchTransactions -> viewModelScope.launch {
                repository.fetchTransactions().collectLatest { result ->
                    when (result) {
                        is Resource.Loading -> _state.value =
                            _state.value.copy(isLoading = true)

                        is Resource.Error -> {
                            _state.value = _state.value.copy(isLoading = false)
                            channel.send(
                                UiEvent.ShowErrorMessage(
                                    result.message ?: "Terjadi kesalahan"
                                )
                            )
                        }

                        is Resource.Success -> _state.value = _state.value.copy(
                            isLoading = false,
                            transactions = result.data ?: emptyList()
                        )
                    }
                }
            }

            CalendarEvent.FetchBalance -> viewModelScope.launch {
                repository.fetchBalance().collectLatest { result ->
                    when (result) {
                        is Resource.Loading -> _state.value =
                            _state.value.copy(isLoading = true)

                        is Resource.Error -> {
                            _state.value = _state.value.copy(isLoading = false)
                            channel.send(
                                UiEvent.ShowErrorMessage(
                                    result.message ?: "Terjadi kesalahan"
                                )
                            )
                        }

                        is Resource.Success -> _state.value = _state.value.copy(
                            isLoading = false,
                            totalMoney = result.data ?: 0
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowErrorMessage(val message: String) : UiEvent()
    }

    init {
        onEvent(CalendarEvent.FetchTransactions)
        onEvent(CalendarEvent.FetchBalance)
    }
}