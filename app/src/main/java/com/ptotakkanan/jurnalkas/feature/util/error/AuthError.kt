package com.ptotakkanan.jurnalkas.feature.util.error

sealed class AuthError : Error() {
    data object FieldEmpty : AuthError()
    data object InputTooShort : AuthError()
    data object InvalidEmail : AuthError()
    data object InvalidPassword : AuthError()
    data object PasswordDoesNotMatch : AuthError()
}