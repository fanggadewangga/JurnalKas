package com.ptotakkanan.jurnalkas.feature.profile

data class ProfileState(
    val email: String = "daudwakwaw@gmail.com",
    val name: String = "@daudwakwaw",
    val password: String = "abc123",
    val gender: String = "Pria",
    val phone: String = "085748799995",
    val nik: String = "3507005000000001",
    val isEditable: Boolean = false
)
