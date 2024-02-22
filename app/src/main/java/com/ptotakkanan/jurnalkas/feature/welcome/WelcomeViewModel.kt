package com.ptotakkanan.jurnalkas.feature.welcome

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptotakkanan.jurnalkas.data.repository.AppRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class WelcomeViewModel: ViewModel() {

    private val repository = AppRepository()

    private val channel = Channel<UiEvent>()
    val eventFlow = channel.receiveAsFlow()

    fun onEvent(event: WelcomeEvent) {
        when(event) {
            WelcomeEvent.CheckAuthStatus -> {
                if (repository.checkAuthStatus())
                    viewModelScope.launch { channel.send(UiEvent.NavigateToHome) }
            }
        }
    }

    sealed class UiEvent {
        data object NavigateToHome: UiEvent()
    }

    init {
        onEvent(WelcomeEvent.CheckAuthStatus)
    }
}