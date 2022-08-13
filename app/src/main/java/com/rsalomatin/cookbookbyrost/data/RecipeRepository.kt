package com.rsalomatin.cookbookbyrost.data

import androidx.lifecycle.LiveData

interface RecipeRepository {
    fun getAll(): LiveData<List<Recipe>>
    fun likeByID(id: Long)
    fun deleteByID(id: Long)
    fun save(recipe: Recipe)

    companion object {
        const val NEW_RECIPE_ID = 0L

        val CATEGORY = listOf(
            "Европейская",
            "Азиатская",
            "Паназиатская",
            "Восточная",
            "Американская",
            "Русская",
            "Средиземноморская"
        )

    }

}