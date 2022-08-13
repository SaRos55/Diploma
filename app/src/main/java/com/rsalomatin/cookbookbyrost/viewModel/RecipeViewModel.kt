package com.rsalomatin.cookbookbyrost.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rsalomatin.cookbookbyrost.data.Recipe
import com.rsalomatin.cookbookbyrost.data.RecipeRepository
import com.rsalomatin.cookbookbyrost.data.RecipeRepository.Companion.CATEGORY
import com.rsalomatin.cookbookbyrost.data.RecipeRepositoryImpl
import com.rsalomatin.cookbookbyrost.db.AppDb

class RecipeViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository: RecipeRepository =
        RecipeRepositoryImpl(
            dao = AppDb.getInstance(
                context = application
            ).recipeDao
        )

    val data = repository.getAll()

    private val currentRecipe = MutableLiveData<Recipe?>(null)

    val dataFilters = MutableLiveData<Map<Int, String>>()

    private var filteredData = listOf<Recipe>()

    fun updateFilteredData(filtersList: List<Recipe>): List<Recipe> {
        filteredData = filtersList.filter {
            it.category in (dataFilters.value?.values ?: CATEGORY)
        }
        return filteredData
    }

    fun getFoundData(searchText: String): List<Recipe> {
        return filteredData.filter {
            it.title.lowercase().contains(searchText.lowercase())
        }
    }

    fun lykeByID(id: Long) = repository.likeByID(id)
    fun deleteByID(id: Long) = repository.deleteByID(id)

    fun onSaveButtonClicked(
        category: String,
        title: String,
        author: String,
        steps: String
    ) {
        if (category.isBlank()
            || title.isBlank()
            || author.isBlank()
            || steps.isBlank()
        ) return

        val recipe = currentRecipe.value?.copy(
            category = category,
            title = title,
            author = author,
            steps = steps
        ) ?: Recipe(
            id = RecipeRepository.NEW_RECIPE_ID,
            category = category,
            title = title,
            author = author,
            steps = steps
        )

        repository.save(recipe)
        currentRecipe.value = null
    }

    fun onEditButtonClicked(recipe: Recipe) {
        currentRecipe.value = recipe
    }

    fun onClickFilter(checkList: Map<Int, String>) {
        dataFilters.value = checkList
    }
}