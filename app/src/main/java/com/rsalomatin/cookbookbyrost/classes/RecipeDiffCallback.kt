package com.rsalomatin.cookbookbyrost.classes

import androidx.recyclerview.widget.DiffUtil
import com.rsalomatin.cookbookbyrost.data.Recipe

class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }
}