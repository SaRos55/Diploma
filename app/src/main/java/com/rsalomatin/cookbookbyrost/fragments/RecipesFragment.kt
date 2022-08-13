package com.rsalomatin.cookbookbyrost.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rsalomatin.cookbookbyrost.R
import com.rsalomatin.cookbookbyrost.classes.RecipeInteractionListener
import com.rsalomatin.cookbookbyrost.classes.RecipesAdapter
import com.rsalomatin.cookbookbyrost.data.Recipe
import com.rsalomatin.cookbookbyrost.databinding.FragmentRecipesBinding
import com.rsalomatin.cookbookbyrost.fragments.RecipeEditFragment.Companion.recipeArg
import com.rsalomatin.cookbookbyrost.viewModel.RecipeViewModel

class RecipesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRecipesBinding.inflate(inflater, container, false)

        val viewModel: RecipeViewModel by viewModels(ownerProducer = ::requireParentFragment)

        val adapter = RecipesAdapter(object : RecipeInteractionListener {
            override fun onLikeListener(recipe: Recipe) {
                viewModel.lykeByID(recipe.id)
            }

            override fun onDeleteListener(recipe: Recipe) {
                viewModel.deleteByID(recipe.id)
            }

            override fun onEditListener(recipe: Recipe) {
                viewModel.onEditButtonClicked(recipe)
                findNavController().navigate(
                    R.id.action_recipes_item_to_recipeEditFragment,
                    Bundle().apply {
                        recipeArg = arrayListOf(
                            recipe.category,
                            recipe.title,
                            recipe.author,
                            recipe.steps
                        )
                    }
                )
            }
        })

        binding.recipesList.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner) { recipes ->
            viewModel.updateFilteredData(recipes)
            val recipesList = viewModel.getFoundData(binding.filterEdittext.text.toString())
            adapter.submitList(recipesList)
            binding.recipesCap.isVisible = (recipesList.isEmpty())
        }


        binding.filterEdittext.addTextChangedListener { editable ->
            adapter.submitList(viewModel.getFoundData(editable.toString()))
        }


        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_recipes_item_to_recipeEditFragment)
        }

        return binding.root
    }
}