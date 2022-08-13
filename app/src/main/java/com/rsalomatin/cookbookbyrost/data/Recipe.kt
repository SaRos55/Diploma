package com.rsalomatin.cookbookbyrost.data

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: Long,
    val title: String,
    val author: String,
    val category: String = RecipeRepository.CATEGORY[5],
    val liked: Boolean = false,
    val steps: String
)