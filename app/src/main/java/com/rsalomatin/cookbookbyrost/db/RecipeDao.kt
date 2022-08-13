package com.rsalomatin.cookbookbyrost.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes ORDER BY id DESC")
    fun getAll(): LiveData<List<RecipeEntity>>

    @Query("SELECT * FROM recipes ORDER BY id DESC")
    fun getRecipes(): List<RecipeEntity>

    @Insert
    fun insert(recipe: RecipeEntity)

    @Query(
        """
            UPDATE recipes SET
            category = :category,
            title = :title,
            author = :author,
            steps = :steps,
            liked = :liked
            WHERE id = :id
        """
    )
    fun updateRecipeById(
        id: Long,
        category: String,
        title: String,
        author: String,
        steps: String,
        liked: Boolean
    )

    @Query(
        """
            UPDATE recipes SET
             liked = CASE WHEN liked THEN 0 ELSE 1 END
            WHERE id = :id
        """
    )
    fun likeById(id: Long)

    @Query("DELETE FROM recipes WHERE id = :id")
    fun removeById(id: Long)
}