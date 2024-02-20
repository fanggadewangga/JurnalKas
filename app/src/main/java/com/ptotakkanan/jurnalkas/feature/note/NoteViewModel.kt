package com.ptotakkanan.jurnalkas.feature.note

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.domain.Note
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NoteViewModel : ViewModel() {

    private val _state = mutableStateOf(NoteState())
    val state = _state

    private val _dummyNote = listOf(
        Note("Part-time", icon = R.drawable.ic_developer, nominal = 500000, isIncome = true),
        Note("Pancong", icon = R.drawable.ic_chocolate, nominal = 300000, isOutcome = true),
        Note("Bus", icon = R.drawable.ic_traffic, nominal = 20000, isOutcome = true),
    ).toMutableStateList()

    val dummyNote: List<Note>
        get() = _dummyNote

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.EnterSearchQuery -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(
                        query = event.value,
                        isLoading = true
                    )
                    delay(3000L)
                    _state.value = _state.value.copy(
                        noteItem = _dummyNote.filter { it.title.contains(event.value) },
                        isLoading = false
                    )
                }
            }
        }
    }

    init {
        state.value.noteItem = dummyNote
    }
}