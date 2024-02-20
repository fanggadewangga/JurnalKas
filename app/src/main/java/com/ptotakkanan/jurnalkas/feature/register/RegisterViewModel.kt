package com.ptotakkanan.jurnalkas.feature.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.login.LoginViewModel
import com.ptotakkanan.jurnalkas.feature.util.state.PasswordTextFieldState
import com.ptotakkanan.jurnalkas.feature.util.state.TextFieldState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {

    private val _emailState = mutableStateOf(TextFieldState())
    val emailState: State<TextFieldState> = _emailState

    private val _passwordState = mutableStateOf(PasswordTextFieldState())
    val passwordState: State<PasswordTextFieldState> = _passwordState

    private val _confirmPasswordState = mutableStateOf(PasswordTextFieldState())
    val confirmPasswordState: State<PasswordTextFieldState> = _confirmPasswordState

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val channel = Channel<UiEvent>()
    val eventFlow = channel.receiveAsFlow()

    fun onEvent(event: RegisterEvent) {
        when(event) {
            is RegisterEvent.EnterEmail -> {
                _emailState.value = _emailState.value.copy(
                    text = event.value
                )
            }

            is RegisterEvent.EnterPassword -> {
                _passwordState.value = _passwordState.value.copy(
                    text = event.value
                )
            }

            is RegisterEvent.EnterConfirmPassword -> {
                _confirmPasswordState.value = _confirmPasswordState.value.copy(
                    text = event.value
                )
            }

            is RegisterEvent.Register -> {
                viewModelScope.launch {
                    _isLoading.value = true
                    delay(2000L)
                    _isLoading.value = false
                    channel.send(UiEvent.ShowToast)
                    delay(2000L)
                    channel.send(UiEvent.NavigateToLogin)
                }
            }
        }
    }

    sealed class UiEvent {
        data object ShowToast : UiEvent()
        data object NavigateToLogin: UiEvent()
    }
}