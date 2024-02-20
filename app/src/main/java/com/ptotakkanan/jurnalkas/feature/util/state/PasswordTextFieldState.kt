package com.ptotakkanan.jurnalkas.feature.util.state

data class PasswordTextFieldState(
    val text: String? = "",
    val error: Error? = null,
    val isPasswordVisible: Boolean = false,
    val isFocus: Boolean = false,
)