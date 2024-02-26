package com.ptotakkanan.jurnalkas.feature.login

sealed class LoginEvent {
    data class EnterEmail(val value: String): LoginEvent()
    data class EnterPassword(val value: String): LoginEvent()
    data object Login: LoginEvent()
    data object Logout: LoginEvent()
}