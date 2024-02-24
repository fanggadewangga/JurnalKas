package com.ptotakkanan.jurnalkas.feature.category.add

import androidx.annotation.DrawableRes
import com.ptotakkanan.jurnalkas.R
import com.ptotakkanan.jurnalkas.domain.Category
import com.ptotakkanan.jurnalkas.feature.util.state.SelectionOption

data class AddCategoryState(
    val isLoading: Boolean = false,
    val imageOptions: List<SelectionOption<String>> = emptyList(),
    val name: String = "",
    val description: String = "",
    val example: String = ""
)
