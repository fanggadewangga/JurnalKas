package com.ptotakkanan.jurnalkas.feature.profile

data class ProfileState(
    val isLoading: Boolean = false,
    val uid: String = "",
    val email: String = "daudwakwaw@gmail.com",
    val name: String = "Daud The Kid",
    val username: String = "@daudwakwaw",
    val description: String = "A man who can't be moved",
    val password: String = "abc123",
    val gender: String = "Pria",
    val phone: String = "085748799995",
    val nik: String = "3507005000000001",
    val isEditable: Boolean = false
)
