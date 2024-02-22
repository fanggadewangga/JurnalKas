package com.ptotakkanan.jurnalkas.feature.category.add

import com.ptotakkanan.jurnalkas.domain.Category
import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption

sealed class AddCategoryEvent {
    data class EnterCategory(val value: String): AddCategoryEvent()
    data class EnterDescription(val value: String): AddCategoryEvent()
    data class ChooseCategory(val value: SelectionOption<Int>): AddCategoryEvent()
}