package com.ptotakkanan.jurnalkas.feature.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptotakkanan.jurnalkas.data.Resource
import com.ptotakkanan.jurnalkas.data.repository.AppRepository
import com.ptotakkanan.jurnalkas.feature.util.state.PasswordTextFieldState
import com.ptotakkanan.jurnalkas.feature.util.state.TextFieldState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

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

    private val repository = AppRepository()

    fun onEvent(event: RegisterEvent) {
        when (event) {
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
                    if (passwordState.value.text.toString() != confirmPasswordState.value.text.toString())
                        channel.send(UiEvent.ShowErrorToast("Password tidak sesuai"))
                    else {
                        _isLoading.value = true
                        repository.register(
                            emailState.value.text.toString(),
                            passwordState.value.toString(),
                        ).collectLatest { result ->
                            when (result) {
                                is Resource.Error -> {
                                    _isLoading.value = false
                                    channel.send(UiEvent.ShowErrorToast(result.message.toString()))
                                }

                                is Resource.Loading -> {
                                    _isLoading.value = true
                                }

                                is Resource.Success -> {
                                    _isLoading.value = false
                                    channel.send(UiEvent.ShowSuccessToast("Berhasil Register"))
                                    delay(2000L)
                                    channel.send(UiEvent.NavigateToLogin)
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    sealed class UiEvent {
        data class ShowSuccessToast(val message: String) : UiEvent()
        data class ShowErrorToast(val message: String) : UiEvent()
        data object NavigateToLogin : UiEvent()
    }
}