package com.ptotakkanan.jurnalkas.feature.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.util.state.PasswordTextFieldState
import com.ptotakkanan.jurnalkas.feature.util.state.TextFieldState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val _emailState = mutableStateOf(TextFieldState())
    val emailState: State<TextFieldState> = _emailState

    private val _passwordState = mutableStateOf(PasswordTextFieldState())
    val passwordState: State<PasswordTextFieldState> = _passwordState

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val channel = Channel<UiEvent>()
    val eventFlow = channel.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.EnterEmail -> {
                _emailState.value = _emailState.value.copy(
                    text = event.value,
                    isFocus = true
                )
                _passwordState.value = _passwordState.value.copy(
                    isFocus = false
                )
            }

            is LoginEvent.EnterPassword -> {
                _passwordState.value = _passwordState.value.copy(
                    text = event.value,
                    isFocus = true
                )
                _emailState.value = _emailState.value.copy(
                    isFocus = false
                )
            }

            is LoginEvent.SetEmailFocus -> {
                _emailState.value = _emailState.value.copy(
                    isFocus = true
                )
                _passwordState.value = _passwordState.value.copy(
                    isFocus = false
                )
            }

            is LoginEvent.SetPasswordFocus -> {
                _emailState.value = _emailState.value.copy(
                    isFocus = false
                )
                _passwordState.value = _passwordState.value.copy(
                    isFocus = true
                )
            }

            is LoginEvent.Login -> {
                viewModelScope.launch {
                    _isLoading.value = true
                    delay(2000L)
                    _isLoading.value = false
                    channel.send(UiEvent.ShowToast(R.string.success_login.toString()))
                    delay(2000L)
                    channel.send(UiEvent.NavigateToHomeScreen)
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowToast(val message: String): UiEvent()
        data object NavigateToHomeScreen: UiEvent()
    }
}