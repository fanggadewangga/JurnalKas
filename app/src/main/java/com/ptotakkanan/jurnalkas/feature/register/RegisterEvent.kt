package com.ptotakkanan.jurnalkas.feature.register

import com.ptotakkanan.jurnalkas.feature.login.LoginEvent

sealed class RegisterEvent {
    data class EnterEmail(val value: String): RegisterEvent()
    data class EnterPassword(val value: String): RegisterEvent()
    data class EnterConfirmPassword(val value: String): RegisterEvent()
    data object Register: RegisterEvent()
}