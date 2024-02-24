package com.ptotakkanan.jurnalkas.data.model

import com.ptotakkanan.jurnalkas.domain.Category

data class Category(
    val categoryId: String = "",
    val name: String = "",
    val description: String = "",
    val example: String = "",
    val imageUrl: String = "",
) {
    fun toDomain() = Category (categoryId, name, description, example, imageUrl)
}
