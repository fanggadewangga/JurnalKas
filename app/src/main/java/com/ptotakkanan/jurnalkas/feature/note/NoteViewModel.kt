package com.ptotakkanan.jurnalkas.feature.note

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptotakkanan.jurnalkas.data.Resource
import com.ptotakkanan.jurnalkas.data.repository.AppRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class NoteViewModel : ViewModel() {

    private val _state = mutableStateOf(NoteState())
    val state = _state

    private val repository = AppRepository()

    private val channel = Channel<UiEvent>()
    val eventFlow = channel.receiveAsFlow()

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.EnterSearchQuery -> _state.value = _state.value.copy(query = event.value)
            NoteEvent.FetchNotes -> viewModelScope.launch {
                repository.fetchNotes().collectLatest { result ->
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
                        is Resource.Success -> _state.value = _state.value.copy(isLoading = false, notes = result.data ?: emptyList())
                    }
                }
            }

            is NoteEvent.SearchNote -> viewModelScope.launch {
                repository.searchNote(event.query).collectLatest { result ->
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
                        is Resource.Success -> _state.value = _state.value.copy(isLoading = false, notes = result.data ?: emptyList())
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowErrorToast(val message: String) : UiEvent()
    }

    init {
        onEvent(NoteEvent.FetchNotes)
    }
}