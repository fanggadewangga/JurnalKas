package com.ptotakkanan.jurnalkas.feature.login

sealed class LoginEvent {
    data class EnterEmail(val value: String): LoginEvent()
    data class EnterPassword(val value: String): LoginEvent()
    data object SetEmailFocus: LoginEvent()
    data object SetPasswordFocus: LoginEvent()
    data object Login: LoginEvent()
}