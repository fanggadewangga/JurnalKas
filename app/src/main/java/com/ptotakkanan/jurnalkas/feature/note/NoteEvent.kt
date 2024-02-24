package com.ptotakkanan.jurnalkas.feature.note

sealed class NoteEvent {
    data class EnterSearchQuery(val value: String): NoteEvent()
    data object FetchNotes: NoteEvent()
    data class SearchNote(val query: String): NoteEvent()
}