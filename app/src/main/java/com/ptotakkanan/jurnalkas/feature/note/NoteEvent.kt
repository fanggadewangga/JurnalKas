package com.ptotakkanan.jurnalkas.feature.note

sealed class NoteEvent {
    data class EnterSearchQuery(val value: String): NoteEvent()
}