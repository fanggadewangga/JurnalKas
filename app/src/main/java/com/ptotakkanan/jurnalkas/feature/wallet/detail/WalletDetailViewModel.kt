package com.ptotakkanan.jurnalkas.feature.wallet.detail

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptotakkanan.jurnalkas.data.Resource
import com.ptotakkanan.jurnalkas.data.repository.AppRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class WalletDetailViewModel : ViewModel() {

    private val _state = mutableStateOf(WalletDetailState())
    val state = _state

    private val repository = AppRepository()

    private val channel = Channel<UiEvent>()
    val eventFlow = channel.receiveAsFlow()

    @SuppressLint("NewApi")
    fun onEvent(event: WalletDetailEvent) {
        when (event) {
            is WalletDetailEvent.OpenDatePicker -> _state.value =
                _state.value.copy(isDatePickerOpen = event.isOpen)

            is WalletDetailEvent.SetRange -> _state.value =
                _state.value.copy(startDateRange = event.start, endDateRange = event.end)

            is WalletDetailEvent.SetSheetOpen -> _state.value =
                _state.value.copy(isSheetOpen = event.isOpen)

            is WalletDetailEvent.FetchInRangeWalletDetail -> viewModelScope.launch {
                repository.fetchInRangeWalletDetail(
                    event.walletId,
                    event.start,
                    event.end
                ).collectLatest { result ->
                    when (result) {
                        is Resource.Loading -> _state.value = _state.value.copy(isLoading = true)
                        is Resource.Error -> {
                            _state.value = _state.value.copy(isLoading = true)
                            channel.send(
                                UiEvent.ShowErrorMessage(
                                    result.message ?: "Terjadi kesalahan"
                                )
                            )
                        }

                        is Resource.Success -> _state.value =
                            _state.value.copy(isLoading = false, walletDetailInRange = result.data)
                    }
                }
            }
        }
    }

    fun fetchWalletDetail(walletId: String) {
        viewModelScope.launch {
            repository.fetchWalletDetail(walletId).collectLatest { result ->
                when (result) {
                    is Resource.Loading -> _state.value = _state.value.copy(isLoading = true)
                    is Resource.Error -> {
                        _state.value = _state.value.copy(isLoading = false)
                        channel.send(
                            UiEvent.ShowErrorMessage(
                                result.message ?: "Terjadi Kesalahan"
                            )
                        )
                    }

                    is Resource.Success -> _state.value =
                        _state.value.copy(isLoading = false, walletDetail = result.data)
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowErrorMessage(val message: String) : UiEvent()
    }
}