package com.rsalomatin.cookbookbyrost.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
class RecipeEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    val category: String,
    val title: String,
    val author: String,
    val steps: String,

    @ColumnInfo(name = "liked")
    val liked: Boolean = false
)