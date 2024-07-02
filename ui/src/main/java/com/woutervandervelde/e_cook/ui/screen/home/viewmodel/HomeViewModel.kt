package com.woutervandervelde.e_cook.ui.screen.home.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    var recipes: MutableState<List<Recipe>> = mutableStateOf<List<Recipe>>(listOf())

    init {
        loadRecipes()
    }

    fun loadRecipes() {
        CoroutineScope(Dispatchers.IO).launch {
             recipes.value = recipeRepository.getAllRecipe()
        }
    }

    fun saveRecipe(name: String, description: String) {
        CoroutineScope(Dispatchers.IO).launch {
            recipeRepository.insertRecipe(Recipe(name, description))
        }
    }
}