package com.ptotakkanan.jurnalkas.feature.analysis

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptotakkanan.jurnalkas.data.Resource
import com.ptotakkanan.jurnalkas.data.repository.AppRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AnalysisViewModel : ViewModel() {

    val tabOptions = listOf("Pemasukan", "Pengeluaran")

    private val _state = mutableStateOf(AnalysisState())
    val state: State<AnalysisState> = _state

    private val repository = AppRepository()

    private val channel = Channel<UiEvent>()
    val eventFlow = channel.receiveAsFlow()

    fun onEvent(event: AnalysisEvent) {
        when (event) {
            is AnalysisEvent.FetchIncome -> viewModelScope.launch {
                repository.fetchAnalysis(true, event.walletId).collectLatest { result ->
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

                        is Resource.Success -> {
                            _state.value = _state.value
                                .copy(
                                    isLoading = false,
                                    balance = result.data?.balance ?: 0,
                                    thisMonthIncome = result.data?.thisMonthNominal ?: 0,
                                    incomeTransactions = result.data?.transactions ?: emptyList(),
                                )
                        }
                    }
                }
            }

            is AnalysisEvent.FetchOutcome -> viewModelScope.launch {
                repository.fetchAnalysis(false, event.walletId).collectLatest { result ->
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

                        is Resource.Success -> {
                            _state.value = _state.value
                                .copy(
                                    isLoading = false,
                                    balance = result.data?.balance ?: 0,
                                    thisMonthOutcome = result.data?.thisMonthNominal ?: 0,
                                    outcomeTransactions = result.data?.transactions ?: emptyList(),
                                )
                        }
                    }
                }
            }

            is AnalysisEvent.SwitchTab -> _state.value = _state.value.copy(selectedTab = event.tab)
        }
    }

    sealed class UiEvent {
        data class ShowErrorToast(val message: String) : UiEvent()
    }
}