package com.ptotakkanan.jurnalkas.feature.input

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.domain.Category
import com.ptotakkanan.jurnalkas.domain.Wallet
import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption

class InputViewModel: ViewModel() {

    private val _outcomeCategories = listOf(
        SelectionOption(Category("Makan", R.drawable.ic_food), true),
        SelectionOption(Category("Jajan", R.drawable.ic_snack), false),
        SelectionOption(Category("Harian", R.drawable.ic_broom), false),
        SelectionOption(Category("Lalu Lintas", R.drawable.ic_traffic), false),
        SelectionOption(Category("Hadiah", R.drawable.ic_gift), false),
        SelectionOption(Category("Rumah", R.drawable.ic_house), false),
        SelectionOption(Category("Pendidikan", R.drawable.ic_education), false),
        SelectionOption(Category("Hiburan", R.drawable.ic_entertaiment), false),
    ).toMutableStateList()

    val outcomeCategories: List<SelectionOption<Category>>
        get() = _outcomeCategories

    private val _wallets = listOf(
        SelectionOption(Wallet("BCA", R.drawable.iv_bca), false),
        SelectionOption(Wallet("Shopee Pay", R.drawable.iv_sppay), false),
        SelectionOption(Wallet("Mandiri", R.drawable.iv_mandiri), false),
        SelectionOption(Wallet("Tunai", R.drawable.ic_wallet_category), true),
    ).toMutableStateList()

    val wallets: List<SelectionOption<Wallet>>
        get() = _wallets

    private val _tabOptions = listOf(
        SelectionOption("Dompet", false),
        SelectionOption("Kategori", true),
    ).toMutableStateList()

    val tabOptions: List<SelectionOption<String>>
        get() = _tabOptions

    private val _numpadNumbers = listOf("7", "8", "9", "4", "5", "6", "1", "2", "3")
    val numpadNumbers: List<String>
        get() = _numpadNumbers

    private val _state = mutableStateOf(InputState())
    val state = _state

    fun onEvent(event: InputEvent) {
        when(event) {
            is InputEvent.EnterDescription -> {
                _state.value = _state.value.copy(description = event.value)
            }

            is InputEvent.ChooseOutcomeCategory -> {
                _outcomeCategories.forEach { it.selected = false }
                _outcomeCategories.find { it.option == event.value.option }?.selected = true
                _state.value = _state.value.copy(chosenOutcomeCategory = event.value.option.category)
            }
            is InputEvent.ChooseWalletCategory -> {
                _wallets.forEach { it.selected = false }
                _wallets.find { it.option == event.value.option }?.selected = true
                _state.value = _state.value.copy(chosenOutcomeCategory = event.value.option.name)
            }
            is InputEvent.EnterDate -> {
                _state.value = _state.value.copy(date = event.value)
            }
            is InputEvent.EnterNominal -> {
                _state.value = _state.value.copy(date = event.value)
            }
            is InputEvent.SwitchTab -> {
                _tabOptions.forEach { it.selected = false }
                _tabOptions.find { it.option == event.value.option }?.selected = true
                _state.value = _state.value.copy(selectedTab = event.value.option)
            }
            is InputEvent.DeleteNominalByNumpad -> {

            }
            is InputEvent.EnterNominalByNumpad -> {

            }
        }
    }

    fun selectedOutcomeCategory() = _outcomeCategories.first { it.selected }.option
    fun selectedWallet() = _wallets.first { it.selected }.option
}