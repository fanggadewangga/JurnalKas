package com.ptotakkanan.jurnalkas.data.model

import com.ptotakkanan.jurnalkas.domain.Note

data class Note(
    val title: String,
    val imageUrl: String,
    val nominal: Long,
    val isIncome: Boolean,
) {
    fun toDomain() = Note(title, imageUrl, nominal, isIncome)
}
