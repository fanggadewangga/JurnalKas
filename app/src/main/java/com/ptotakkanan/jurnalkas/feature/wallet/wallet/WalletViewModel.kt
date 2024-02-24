package com.ptotakkanan.jurnalkas.feature.wallet.wallet

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptotakkanan.jurnalkas.data.Resource
import com.ptotakkanan.jurnalkas.data.repository.AppRepository
import kotlinx.coroutines.launch

class WalletViewModel : ViewModel() {

    private val _state = mutableStateOf(WalletState())
    val state = _state

    private val repository = AppRepository()

    private fun fetchWallets() {
        viewModelScope.launch {
            repository.fetchWallets().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = _state.value.copy(isLoading = false)
                    }

                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            wallets = result.data ?: emptyList()
                        )
                    }
                }
            }
        }
    }

    init {
        fetchWallets()
    }
}