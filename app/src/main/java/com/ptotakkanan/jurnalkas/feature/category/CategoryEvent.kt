package com.ptotakkanan.jurnalkas.feature.category

import com.ptotakkanan.jurnalkas.domain.Category
import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption

sealed class CategoryEvent {
    data class SelectCategory(val selectionOption: SelectionOption<Category>): CategoryEvent()
    data class AddMoreCategory(val selectionOption: SelectionOption<Category>): CategoryEvent()
    data object NavigateToMoreCategory: CategoryEvent()
}