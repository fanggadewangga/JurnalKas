package com.ptotakkanan.jurnalkas.feature.tax

import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption

sealed class TaxEvent {
    data class EnterNik(val value: String): TaxEvent()
    data class ChooseNPWP(val value: SelectionOption<String>): TaxEvent()
    data class EnterMarriageStatus(val value: Boolean): TaxEvent()
    data class EnterSalary(val value: Long): TaxEvent()
    data class ShowResult(val value: Boolean): TaxEvent()
}