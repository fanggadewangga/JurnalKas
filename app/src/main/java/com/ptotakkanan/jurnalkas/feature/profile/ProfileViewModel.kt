package com.ptotakkanan.jurnalkas.feature.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptotakkanan.jurnalkas.data.Resource
import com.ptotakkanan.jurnalkas.data.model.User
import com.ptotakkanan.jurnalkas.data.repository.AppRepository
import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val _state = mutableStateOf(ProfileState())
    val state = _state

    private val _gender = listOf(
        SelectionOption("Pria", true),
        SelectionOption("Wanita", false),
    )

    private val channel = Channel<UiEvent>()
    val eventFlow = channel.receiveAsFlow()

    private val repository = AppRepository()

    fun onEvent(event: ProfileEvent) {
        when (event) {
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

            is ProfileEvent.FetchProfile -> viewModelScope.launch {
                repository.fetchUserDetail().collectLatest { result ->
                    when (result) {
                        is Resource.Loading -> _state.value = _state.value.copy(isLoading = true)
                        is Resource.Error -> {
                            _state.value = _state.value.copy(isLoading = false)
                            channel.send(
                                UiEvent.ShowErrorMessage(
                                    result.message ?: "Terjadi kesalahan"
                                )
                            )
                        }

                        is Resource.Success -> {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                uid = result.data?.uid ?: "",
                                email = result.data?.email ?: "",
                                name = result.data?.name ?: "",
                                username = result.data?.username ?: "",
                                gender = result.data?.gender ?: "",
                                phone = result.data?.phone ?: "",
                                nik = result.data?.nik ?: "",
                            )
                        }
                    }
                }
            }

            ProfileEvent.UpdateUserData -> viewModelScope.launch {
                val body = User(
                    uid = _state.value.uid,
                    email = _state.value.email,
                    name = _state.value.name,
                    gender = _state.value.gender,
                    phone = _state.value.phone,
                    nik = _state.value.nik,
                    description = _state.value.description,
                    username = _state.value.name,
                )
                repository.updateUserData(body).collectLatest { result ->
                    when(result) {
                        is Resource.Loading -> _state.value = _state.value.copy(isLoading = true)
                        is Resource.Error ->{
                            _state.value = _state.value.copy(isLoading = false)
                            channel.send(
                                UiEvent.ShowErrorMessage(
                                    result.message ?: "Terjadi kesalahan"
                                )
                            )
                        }
                        is Resource.Success -> {
                            _state.value = _state.value.copy(isLoading = false)
                            onEvent(ProfileEvent.SwitchToEditable(false))
                            onEvent(ProfileEvent.FetchProfile)
                        }
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data object SwitchToEditable : UiEvent()
        data class ShowErrorMessage(val message: String) : UiEvent()
    }
}