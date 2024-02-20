package com.ptotakkanan.jurnalkas.feature.category

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.domain.Category
import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private val _pageState = mutableIntStateOf(1)
    val pageState = _pageState

    private val _categories = listOf(
        SelectionOption(Category("Makan", R.drawable.ic_food), false),
        SelectionOption(Category("Jajan", R.drawable.ic_snack), false),
        SelectionOption(Category("Harian", R.drawable.ic_broom), false),
        SelectionOption(Category("Lalu Lintas", R.drawable.ic_traffic), false),
        SelectionOption(Category("Hadiah", R.drawable.ic_gift), false),
        SelectionOption(Category("Rumah", R.drawable.ic_house), false),
        SelectionOption(Category("Pendidikan", R.drawable.ic_education), false),
        SelectionOption(Category("Hiburan", R.drawable.ic_entertaiment), false),
        SelectionOption(Category("Komunikasi", R.drawable.ic_communication), false),
    ).toMutableStateList()

    val category: List<SelectionOption<Category>>
        get() = _categories

    private val _moreCategories = listOf(
        SelectionOption(Category("Profil", R.drawable.ic_profile), false),
        SelectionOption(Category("Book", R.drawable.ic_book), false),
        SelectionOption(Category("Dompet", R.drawable.ic_wallet_category), false),
        SelectionOption(Category("Cari", R.drawable.ic_search), false),
        SelectionOption(Category("Pengingat", R.drawable.ic_reminder), false),
        SelectionOption(Category("Kategori", R.drawable.ic_ticker), false),
        SelectionOption(Category("Tentang", R.drawable.ic_about), false),
        SelectionOption(Category("Google Drive", R.drawable.ic_gdrive), false),
        SelectionOption(Category("Premium", R.drawable.ic_badge), false),
    )

    val moreCategory: List<SelectionOption<Category>>
        get() = _moreCategories

    private val channel = Channel<UiEvent>()
    val eventFlow = channel.receiveAsFlow()

    fun onEvent(event: CategoryEvent) {
        when(event) {
            is CategoryEvent.SelectCategory -> {
                _categories.forEach { it.selected = false }
                _categories.find { it.option == event.selectionOption.option }?.selected = true
                viewModelScope.launch { channel.send(UiEvent.ShowToast) }
            }

            is CategoryEvent.AddMoreCategory -> {
                _moreCategories.find { it.option == event.selectionOption.option }?.selected = true
                if (!_categories.contains(event.selectionOption)) _categories.add(event.selectionOption)
                viewModelScope.launch { channel.send(UiEvent.NavigateToCategories) }
            }

            is CategoryEvent.NavigateToMoreCategory -> {
                _pageState.intValue = 2
                viewModelScope.launch { channel.send(UiEvent.NavigateToMoreCategories) }
            }
        }
    }

    fun isCategorySelected(): Boolean {
        _categories.forEach { if (it.selected) return true }
        return false
    }

    private fun selectedCategory() = _categories.first { it.selected }.option

    sealed class UiEvent {
        data object ShowToast : UiEvent()
        data object NavigateToCategories : UiEvent()
        data object NavigateToMoreCategories : UiEvent()
    }
}