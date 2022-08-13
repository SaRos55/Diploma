package com.rsalomatin.cookbookbyrost.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rsalomatin.cookbookbyrost.data.RecipeRepository.Companion.CATEGORY
import com.rsalomatin.cookbookbyrost.databinding.FragmentFiltersBinding
import com.rsalomatin.cookbookbyrost.viewModel.RecipeViewModel

class RecipesFiltersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFiltersBinding.inflate(inflater, container, false)

        val viewModel: RecipeViewModel by viewModels(ownerProducer = ::requireParentFragment)

        fun updateCheckList() {
            val mapFilters = mutableMapOf<Int, String>()
            for ((index, view) in binding.checkboxGroup.children.withIndex()) {
                if (view is CheckBox) {
                    if (view.isChecked) mapFilters[view.id] = CATEGORY[index]
                }
            }
            viewModel.onClickFilter(mapFilters)
        }

        val checkboxClickListener = View.OnClickListener {
            updateCheckList()
        }

        viewModel.dataFilters.observe(viewLifecycleOwner) { checkList ->
            if (checkList.size == 1) {
                binding.root.findViewById<CheckBox>(checkList.keys.first()).isEnabled = false
            } else {
                for (c in checkList) {
                    binding.root.findViewById<CheckBox>(c.key).isEnabled = true
                }
            }
        }

        for (view in binding.checkboxGroup.children) {
            if (view is CheckBox) {
                view.setOnClickListener(checkboxClickListener)
            }
        }

        return binding.root
    }
}