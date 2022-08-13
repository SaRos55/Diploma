package com.rsalomatin.cookbookbyrost.classes

import com.rsalomatin.cookbookbyrost.data.Recipe

interface RecipeInteractionListener {
    fun onLikeListener(recipe: Recipe)
    fun onDeleteListener(recipe: Recipe)
    fun onEditListener(recipe: Recipe)
}