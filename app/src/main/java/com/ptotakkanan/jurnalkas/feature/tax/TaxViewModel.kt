package com.ptotakkanan.jurnalkas.feature.tax

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption

class TaxViewModel: ViewModel() {

    private val _npwpOption = listOf(
        SelectionOption("Ya", true),
        SelectionOption("Tidak", false),
    ).toMutableStateList()

    val npwpOption: List<SelectionOption<String>>
        get() = _npwpOption

    private val _state = mutableStateOf(TaxState())
    val state = _state

    fun onEvent(event: TaxEvent) {
        when(event) {
            is TaxEvent.EnterNik -> {
                _state.value = _state.value.copy(nik = event.value)
            }
            is TaxEvent.ChooseNPWP -> {
                _npwpOption.forEach { it.selected = false }
                _npwpOption.find { it.option == event.value.option }?.selected = true
            }
            is TaxEvent.EnterMarriageStatus -> {
                _state.value = _state.value.copy(marriageStatus = event.value)
            }
            is TaxEvent.EnterSalary -> {
                _state.value = _state.value.copy(salary = event.value)
            }
            is TaxEvent.ShowResult -> {
                _state.value = _state.value.copy(isHasResult = event.value)
            }
        }
    }
}