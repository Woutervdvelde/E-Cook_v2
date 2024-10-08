package com.woutervandervelde.e_cook.ui.screen.home.presentation

import android.util.Log
import com.woutervandervelde.e_cook.domain.model.Ingredient
import com.woutervandervelde.e_cook.domain.model.MeasurementUnit
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.domain.model.RecipeIngredient
import com.woutervandervelde.e_cook.domain.model.Source
import com.woutervandervelde.e_cook.domain.model.Tag
import com.woutervandervelde.e_cook.domain.repository.IngredientRepository
import com.woutervandervelde.e_cook.domain.repository.RecipeRepository
import com.woutervandervelde.e_cook.ui.screen.home.navigation.HomeNavEvent
import com.woutervandervelde.e_cook.ui.viewmodel.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = HomeViewModel.Factory::class)
class HomeViewModel @AssistedInject constructor(
    private val recipeRepository: RecipeRepository,
    private val ingredientRepository: IngredientRepository,
    @Assisted private val navEvent: (HomeNavEvent) -> Unit
) : BaseViewModel<HomeUiState, HomeUiEvent>() {
    init {
        loadRecipes()
    }

    override fun onUiEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.OnAddRecipeClick -> navEvent(HomeNavEvent.ToNewRecipe)
            HomeUiEvent.OnRandomRecipeClick -> {

            }

            is HomeUiEvent.OnRecipeClick -> navEvent(HomeNavEvent.ToRecipe(event.recipeId))
        }
    }

    private fun loadRecipes() {
        CoroutineScope(Dispatchers.IO).launch {
            _uiState.update {
                it.copy(
                    recipes = recipeRepository.getAllRecipe()
                )
            }
        }
    }

    override fun defaultUiState(): HomeUiState = HomeUiState()

    @AssistedFactory
    interface Factory {
        fun create(navEvent: (HomeNavEvent) -> Unit): HomeViewModel
    }
}