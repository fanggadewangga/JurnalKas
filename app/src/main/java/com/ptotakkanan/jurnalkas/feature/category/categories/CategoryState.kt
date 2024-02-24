package com.ptotakkanan.jurnalkas.feature.category.categories

import com.ptotakkanan.jurnalkas.domain.Category

data class CategoryState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
)
