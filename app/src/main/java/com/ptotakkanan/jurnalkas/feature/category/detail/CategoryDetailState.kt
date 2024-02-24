package com.ptotakkanan.jurnalkas.feature.category.detail

import com.ptotakkanan.jurnalkas.domain.Category

data class CategoryDetailState(
    val isLoading: Boolean = false,
    val category: Category? = null
)
