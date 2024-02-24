package com.ptotakkanan.jurnalkas.feature.profile

sealed class ProfileEvent {
    data class EnterEmail(val value: String): ProfileEvent()
    data class EnterName(val value: String): ProfileEvent()
    data class EnterPassword(val value: String): ProfileEvent()
    data class EnterGender(val value: String): ProfileEvent()
    data class EnterPhone(val value: String): ProfileEvent()
    data class EnterNik(val value: String): ProfileEvent()
    data class SwitchToEditable(val value: Boolean) : ProfileEvent()
    data object FetchProfile: ProfileEvent()
    data object UpdateUserData: ProfileEvent()
}