package com.ptotakkanan.jurnalkas.feature.input

import com.ptotakkanan.jurnalkas.domain.Wallet
import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption

data class InputState(
    val wallet: List<SelectionOption<Wallet>> = emptyList(),
    val isLoading: Boolean = false,
    val chosenOutcomeCategory: String = "",
    val chosenWallet: Wallet? = null,
    val description: String = "",
    val nominal: Long = 0,
    val date: String = "Hari Ini",
    val selectedTab: String = "Kategori"
)
