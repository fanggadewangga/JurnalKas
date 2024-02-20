package com.ptotakkanan.jurnalkas.feature.util.state

import com.ptotakkanan.jurnalkas.feature.util.error.Error

data class TextFieldState(
    val text: String? = "",
    val error: Error? = null,
    val isFocus: Boolean = false
)