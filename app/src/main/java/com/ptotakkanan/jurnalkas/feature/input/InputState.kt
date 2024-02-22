package com.ptotakkanan.jurnalkas.feature.input

import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption

data class InputState(
    val isLoading: Boolean = false,
    val chosenOutcomeCategory: String = "",
    val chosenWallet: String = "",
    val description: String = "",
    val nominal: Long = 0,
    val date: String = "Hari Ini",
    val selectedTab: String = "Kategori"
)
