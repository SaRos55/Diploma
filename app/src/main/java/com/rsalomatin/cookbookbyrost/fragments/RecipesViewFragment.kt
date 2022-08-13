package com.rsalomatin.cookbookbyrost.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsalomatin.cookbookbyrost.databinding.FragmentViewRecipeBinding

class RecipesViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentViewRecipeBinding.inflate(inflater, container, false)

        return binding.root
    }
}