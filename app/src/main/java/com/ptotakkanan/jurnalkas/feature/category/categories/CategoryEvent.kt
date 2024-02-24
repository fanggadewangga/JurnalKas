package com.ptotakkanan.jurnalkas.feature.category.categories

sealed class CategoryEvent {
    data object FetchCategories: CategoryEvent()
    data object NavigateToMoreCategory: CategoryEvent()
}