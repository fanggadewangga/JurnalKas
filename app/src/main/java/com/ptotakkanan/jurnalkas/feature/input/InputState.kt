package com.ptotakkanan.jurnalkas.feature.input

import com.ptotakkanan.jurnalkas.domain.Category
import com.ptotakkanan.jurnalkas.domain.Wallet
import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption

data class InputState(
    val wallet: List<SelectionOption<Wallet>> = emptyList(),
    val outcomeCategories: List<SelectionOption<Category>> = emptyList(),
    val isLoading: Boolean = false,
    val chosenOutcomeCategory: Category? = null,
    val chosenWallet: Wallet? = null,
    val title: String = "",
    val incomeDescription: String = "",
    val outcomeDescription: String = "",
    val incomeNominal: String = "0",
    val outcomeNominal: String = "0",
    val incomeDate: String = "Hari Ini",
    val outcomeDate: String = "Hari Ini",
    val selectedTab: String = "Kategori"
)
