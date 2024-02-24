package com.ptotakkanan.jurnalkas.feature.calendar.detail

import androidx.compose.runtime.State
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

class CalendarDetailViewModel : ViewModel() {

    private val _state = mutableStateOf(CalendarDetailState())
    val state: State<CalendarDetailState> = _state

    private val _options = listOf(
        SelectionOption("Pemasukan", true),
        SelectionOption("Pengeluaran", false)
    ).toMutableStateList()

    val options: List<SelectionOption<String>>
        get() = _options

    private val channel = Channel<UiEvent>()
    val eventFlow = channel.receiveAsFlow()

    private val repository = AppRepository()

    fun onEvent(event: CalendarDetailEvent) {
        when (event) {
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

            is CalendarDetailEvent.FetchTransaction -> viewModelScope.launch {
                repository.fetchTransactions().collectLatest { result ->
                    when (result) {
                        is Resource.Loading -> _state.value = _state.value.copy(isLoading = true)
                        is Resource.Error -> {
                            _state.value = _state.value.copy(isLoading = false)
                            channel.send(
                                UiEvent.ShowErrorToast(
                                    result.message ?: "Terjadi kesalahan"
                                )
                            )
                        }
                        is Resource.Success -> _state.value = _state.value
                            .copy(
                                isLoading = false,
                                transactions = if (event.isIncome)
                                    result.data?.filter { it.isIncome } ?: emptyList()
                                else result.data?.filter { !it.isIncome } ?: emptyList()
                            )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data object ShowIncome : UiEvent()
        data object ShowOutcome : UiEvent()
        data class ShowErrorToast(val message: String) : UiEvent()
    }

    init {
        onEvent(CalendarDetailEvent.FetchTransaction(true))
    }
}