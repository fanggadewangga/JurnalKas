package com.ptotakkanan.jurnalkas.feature.category.add

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption


class AddCategoryViewModel: ViewModel() {

    private val _state = mutableStateOf(AddCategoryState())
    val state = _state

    private val _iconOptions = listOf(
        SelectionOption(R.drawable.ic_book, true),
        SelectionOption(R.drawable.ic_broom, false),
        SelectionOption(R.drawable.ic_traffic, false),
        SelectionOption(R.drawable.ic_food, false),
        SelectionOption(R.drawable.ic_snack, false),
    ).toMutableStateList()

    val iconOption: List<SelectionOption<Int>>
        get() = _iconOptions

    fun onEvent(event: AddCategoryEvent) {
        when(event) {
            is AddCategoryEvent.EnterCategory -> {
                _state.value = _state.value.copy(name = event.value)
            }
            is AddCategoryEvent.ChooseCategory -> {
                _iconOptions.forEach { it.selected = false }
                _iconOptions.find { it.option == event.value.option }?.selected = true
            }
            is AddCategoryEvent.EnterDescription -> {
                _state.value = _state.value.copy(description = event.value)
            }
        }
    }
}