package com.rsalomatin.cookbookbyrost.data

import androidx.lifecycle.map
import com.rsalomatin.cookbookbyrost.db.RecipeDao
import com.rsalomatin.cookbookbyrost.db.toEntity
import com.rsalomatin.cookbookbyrost.db.toModel

class RecipeRepositoryImpl(
    private val dao: RecipeDao
) : RecipeRepository {

    private val data = dao.getAll().map { entities ->
        entities.map { it.toModel() }
    }

    override fun getAll() = data

    override fun likeByID(id: Long) {
        dao.likeById(id)
    }

    override fun deleteByID(id: Long) {
        dao.removeById(id)
    }

    override fun save(recipe: Recipe) {
        if (recipe.id == RecipeRepository.NEW_RECIPE_ID) dao.insert(recipe.toEntity())
        else dao.updateRecipeById(
            recipe.id,
            recipe.category,
            recipe.title,
            recipe.author,
            recipe.steps,
            recipe.liked
        )
    }
}