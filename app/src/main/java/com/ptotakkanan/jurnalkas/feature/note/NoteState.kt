package com.ptotakkanan.jurnalkas.feature.note

import com.ptotakkanan.jurnalkas.domain.Note

data class NoteState(
    val query: String = "",
    var notes: List<Note> = emptyList(),
    val isLoading: Boolean = false
)