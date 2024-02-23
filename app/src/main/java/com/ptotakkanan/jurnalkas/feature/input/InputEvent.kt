package com.ptotakkanan.jurnalkas.feature.input

import com.ptotakkanan.jurnalkas.domain.Category
import com.ptotakkanan.jurnalkas.domain.Wallet
import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption

sealed class InputEvent {
    data class EnterTitle(val value: String): InputEvent()
    data class EnterIncomeDescription(val value: String): InputEvent()
    data class EnterOutcomeDescription(val value: String): InputEvent()
    data class EnterIncomeNominal(val value: String): InputEvent()
    data class EnterOutcomeNominal(val value: String): InputEvent()
    data class EnterIncomeDate(val value: String): InputEvent()
    data class EnterOutcomeDate(val value: String): InputEvent()
    data class ChooseOutcomeCategory(val value: SelectionOption<Category>): InputEvent()
    data class ChooseWalletCategory(val value: SelectionOption<Wallet>): InputEvent()
    data class EnterNominalByNumpad(val value: String): InputEvent()
    data object DeleteNominalByNumpad : InputEvent()
    data class SwitchTab(val value: SelectionOption<String>): InputEvent()
    data object FetchWallets: InputEvent()
    data object AddIncome: InputEvent()
    data object AddOutcome: InputEvent()
}