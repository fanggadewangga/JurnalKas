package com.ptotakkanan.jurnalkas.feature.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {

    private val _state = mutableStateOf(ProfileState())
    val state = _state

    private val _gender = listOf(
        SelectionOption("Pria", true),
        SelectionOption("Wanita", false),
    )

    private val channel = Channel<UiEvent>()
    val eventFlow = channel.receiveAsFlow()

    fun onEvent(event: ProfileEvent) {
        when(event) {
            is ProfileEvent.EnterEmail -> {
                _state.value = _state.value.copy(email = event.value)
            }
            is ProfileEvent.EnterName -> {
                _state.value = _state.value.copy(name = event.value)
            }
            is ProfileEvent.EnterPassword -> {
                _state.value = _state.value.copy(password = event.value)
            }
            is ProfileEvent.EnterGender -> {
                _state.value = _state.value.copy(gender = event.value)
            }
            is ProfileEvent.EnterPhone -> {
                _state.value = _state.value.copy(phone = event.value)
            }
            is ProfileEvent.EnterNik -> {
                _state.value = _state.value.copy(nik = event.value)
            }
            is ProfileEvent.SwitchToEditable -> {
                _state.value = _state.value.copy(isEditable = event.value)
                viewModelScope.launch {
                    channel.send(UiEvent.SwitchToEditable)
                }
            }
        }
    }

    sealed class UiEvent {
        data object SwitchToEditable: UiEvent()
    }
}