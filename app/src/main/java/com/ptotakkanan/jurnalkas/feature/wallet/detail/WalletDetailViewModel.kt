package com.ptotakkanan.jurnalkas.feature.wallet.detail

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.data.Resource
import com.ptotakkanan.jurnalkas.data.repository.AppRepository
import com.ptotakkanan.jurnalkas.domain.Transaction
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class WalletDetailViewModel: ViewModel() {

    private val _state = mutableStateOf(WalletDetailState())
    val state = _state

    private val repository = AppRepository()

    private val channel = Channel<UiEvent>()
    val eventFlow = channel.receiveAsFlow()

    fun fetchWalletDetail(walletId: String) {
        viewModelScope.launch {
            repository.fetchWalletDetail(walletId).collectLatest { result ->
                when(result) {
                    is Resource.Loading -> _state.value = _state.value.copy(isLoading = true)
                    is Resource.Error -> {
                        _state.value = _state.value.copy(isLoading = false)
                        channel.send(UiEvent.ShowErrorMessage(result.message ?: "Terjadi Kesalahan"))
                    }
                    is Resource.Success -> _state.value = _state.value.copy(isLoading = false, walletDetail = result.data)
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowErrorMessage(val message: String): UiEvent()
    }
}