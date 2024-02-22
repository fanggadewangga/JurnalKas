package com.ptotakkanan.jurnalkas.feature.welcome

sealed class WelcomeEvent {
    data object CheckAuthStatus: WelcomeEvent()
}