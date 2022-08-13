package com.rsalomatin.cookbookbyrost.db

import com.rsalomatin.cookbookbyrost.data.Recipe

internal fun RecipeEntity.toModel() = Recipe(
    id = id,
    category = category,
    title = title,
    author = author,
    liked = liked,
    steps = steps
)

internal fun Recipe.toEntity() = RecipeEntity(
    id = id,
    category = category,
    title = title,
    author = author,
    liked = liked,
    steps = steps
)