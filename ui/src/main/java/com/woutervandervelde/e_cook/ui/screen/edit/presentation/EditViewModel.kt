package com.woutervandervelde.e_cook.ui.screen.edit.presentation

import android.util.Log
import com.woutervandervelde.e_cook.domain.model.Ingredient
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.domain.model.RecipeIngredient
import com.woutervandervelde.e_cook.domain.model.RecipeWithIngredients
import com.woutervandervelde.e_cook.domain.repository.IngredientRepository
import com.woutervandervelde.e_cook.domain.repository.RecipeRepository
import com.woutervandervelde.e_cook.ui.screen.edit.navigation.EditNavEvent
import com.woutervandervelde.e_cook.ui.viewmodel.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = EditViewModel.Factory::class)
class EditViewModel @AssistedInject constructor(
    private val ingredientRepository: IngredientRepository,
    private val recipeRepository: RecipeRepository,
    @Assisted private val navEvent: (EditNavEvent) -> Unit,
    @Assisted private val recipeId: Long
) : BaseViewModel<EditUiState, EditUiEvent>() {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val recipe = if (recipeId == -1L) RecipeWithIngredients.Empty() else recipeRepository.getFullRecipeById(recipeId)
            _uiState.update {
                it.copy(
                    recipe = recipe,
                    allIngredients = ingredientRepository.getAllIngredients(),
                    recipeIngredients = recipe.ingredients.toMutableList(),
                    recipeSteps = recipe.recipe.steps.toMutableList()
                )
            }
        }
    }

    override fun onUiEvent(event: EditUiEvent) {
        when (event) {
            EditUiEvent.OnBack, EditUiEvent.OnDiscardRecipe -> navEvent(EditNavEvent.Back)

            is EditUiEvent.OnCreateIngredient -> {
                CoroutineScope(Dispatchers.IO).launch {
                    ingredientRepository.insertIngredient(event.ingredient)
                }
            }

            is EditUiEvent.OnAddIngredientToRecipe ->
                uiState.value.recipeIngredients.add(event.recipeIngredient)

            is EditUiEvent.OnAddStepToRecipe -> {
                val updatedSteps = uiState.value.recipeSteps.toMutableList()
                updatedSteps.add(event.step)
                _uiState.update { it.copy(recipeSteps = updatedSteps) }
            }

            is EditUiEvent.OnDeleteStepFromRecipe -> {
                val updatedSteps = uiState.value.recipeSteps.toMutableList()
                if (event.index in updatedSteps.indices) {
                    updatedSteps.removeAt(event.index)
                    _uiState.update { it.copy(recipeSteps = updatedSteps) }
                }
            }

            is EditUiEvent.OnSaveRecipe -> {
                //TODO
            }
        }
    }

    override fun defaultUiState(): EditUiState = EditUiState()

    @AssistedFactory
    interface Factory {
        fun create(navEvent: (EditNavEvent) -> Unit, recipeId: Long): EditViewModel
    }
}