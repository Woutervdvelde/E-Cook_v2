package com.woutervandervelde.e_cook.ui.screen.recipe.presentation

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.woutervandervelde.e_cook.domain.repository.IngredientRepository
import com.woutervandervelde.e_cook.domain.repository.RecipeRepository
import com.woutervandervelde.e_cook.ui.screen.recipe.navigation.RecipeNavEvent
import com.woutervandervelde.e_cook.ui.viewmodel.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = RecipeViewModel.Factory::class)
class RecipeViewModel @AssistedInject constructor(
    private val recipeRepository: RecipeRepository,
    @Assisted private val navEvent: (RecipeNavEvent) -> Unit,
    @Assisted private val recipeId: Long,
    @ApplicationContext private val context: Context
) : BaseViewModel<RecipeUiState, RecipeUiEvent>() {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val recipe = recipeRepository.getRecipeById(recipeId)
            if (recipe != null) {
                _uiState.update { it.copy(recipe = recipe) }
            } else {
                //TODO tell the user the recipe was not found
            }
        }
    }

    override fun onUiEvent(event: RecipeUiEvent) {
        when (event) {
            RecipeUiEvent.OnBackClick -> navEvent(RecipeNavEvent.Back)

            RecipeUiEvent.OnBookmarkClick -> Toast.makeText(
                context,
                "Bookmarked recipe (feature still in development)",
                Toast.LENGTH_SHORT
            ).show()

            RecipeUiEvent.OnEditClick -> navEvent(RecipeNavEvent.ToRecipeEdit(recipeId))
        }
    }


    override fun defaultUiState(): RecipeUiState = RecipeUiState()

    @AssistedFactory
    interface Factory {
        fun create(navEvent: (RecipeNavEvent) -> Unit, recipeId: Long): RecipeViewModel
    }
}