package com.rsalomatin.cookbookbyrost.classes

import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.rsalomatin.cookbookbyrost.R
import com.rsalomatin.cookbookbyrost.data.Recipe
import com.rsalomatin.cookbookbyrost.databinding.RecipeItemBinding

class RecipeViewHolder(
    private val binding: RecipeItemBinding,
    private val interactionListener: RecipeInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(recipe: Recipe) {
        binding.apply {
            category.text = recipe.category
            title.text = recipe.title
            author.text = recipe.author
            favorite.isChecked = recipe.liked
            favorite.setOnClickListener {
                interactionListener.onLikeListener(recipe)
            }
            body.setOnClickListener {
                interactionListener.onEditListener(recipe)
            }

            recipeMenu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_recipe)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                interactionListener.onDeleteListener(recipe)
                                true
                            }
                            R.id.edit -> {
                                interactionListener.onEditListener(recipe)
                                true
                            }
                            else -> false
                        }

                    }
                }.show()
            }

        }
    }
}