package com.rsalomatin.cookbookbyrost.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rsalomatin.cookbookbyrost.R
import com.rsalomatin.cookbookbyrost.classes.RecipeArg
import com.rsalomatin.cookbookbyrost.data.RecipeRepository.Companion.CATEGORY
import com.rsalomatin.cookbookbyrost.databinding.FragmentEditRecipeBinding
import com.rsalomatin.cookbookbyrost.viewModel.RecipeViewModel

class RecipeEditFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEditRecipeBinding.inflate(inflater, container, false)

        val viewModel: RecipeViewModel by viewModels(ownerProducer = ::requireParentFragment)

        arguments?.recipeArg?.let {
            binding.categoryMenu.editText?.setText(it[0])
            with(binding) {
                editTitle.setText(it[1])
                editAuthor.setText(it[2])
                editRecipe.setText(it[3])
            }
        }

        val adapter = ArrayAdapter(requireContext(), R.layout.category_item, CATEGORY)
        (binding.categoryMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.ok.setOnClickListener {
            val recipeCategory = binding.categoryMenu.editText?.text?.toString()
            val recipeTitle = binding.editTitle.text?.toString()
            val recipeAuthor = binding.editAuthor.text?.toString()
            val recipeText = binding.editRecipe.text?.toString()
            if (!(recipeCategory.isNullOrBlank()
                        || recipeTitle.isNullOrBlank()
                        || recipeAuthor.isNullOrBlank()
                        || recipeText.isNullOrBlank())
            ) viewModel.onSaveButtonClicked(
                recipeCategory,
                recipeTitle,
                recipeAuthor,
                recipeText
            )
            findNavController().navigateUp()
        }
        return binding.root
    }

    companion object {
        var Bundle.recipeArg: ArrayList<String>? by RecipeArg
    }

}

