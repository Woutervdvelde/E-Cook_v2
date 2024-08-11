package com.woutervandervelde.e_cook.ui.screen.recipe.presentation

import com.woutervandervelde.e_cook.domain.repository.IngredientRepository
import com.woutervandervelde.e_cook.domain.repository.RecipeRepository
import com.woutervandervelde.e_cook.ui.screen.recipe.navigation.RecipeNavEvent
import com.woutervandervelde.e_cook.ui.viewmodel.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = RecipeViewModel.Factory::class)
class RecipeViewModel @AssistedInject constructor(
    private val recipeRepository: RecipeRepository,
    @Assisted private val navEvent: (RecipeNavEvent) -> Unit,
    @Assisted private val recipeId: Long
) : BaseViewModel<RecipeUiState, RecipeUiEvent>() {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val recipe = recipeRepository.getRecipeById(recipeId)
            if (recipe != null) {
                _uiState.update { it.copy(recipe = recipe) }
            }
        }
    }

    override fun onUiEvent(event: RecipeUiEvent) {
        TODO("Not yet implemented")
    }


    override fun defaultUiState(): RecipeUiState = RecipeUiState()
    @AssistedFactory
    interface Factory {
        fun create(navEvent: (RecipeNavEvent) -> Unit, recipeId: Long): RecipeViewModel
    }
}