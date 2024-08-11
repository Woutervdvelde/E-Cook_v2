package com.woutervandervelde.e_cook.ui.screen.edit.presentation

import android.util.Log
import com.woutervandervelde.e_cook.domain.model.Ingredient
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.domain.model.RecipeIngredient
import com.woutervandervelde.e_cook.domain.model.RecipeWithIngredients
import com.woutervandervelde.e_cook.domain.model.Source
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
import kotlinx.coroutines.selects.select

@HiltViewModel(assistedFactory = EditViewModel.Factory::class)
class EditViewModel @AssistedInject constructor(
    private val ingredientRepository: IngredientRepository,
    private val recipeRepository: RecipeRepository,
    @Assisted private val navEvent: (EditNavEvent) -> Unit,
    @Assisted private val recipeId: Long
) : BaseViewModel<EditUiState, EditUiEvent>() {

    private lateinit var recipe: RecipeWithIngredients

    init {
        CoroutineScope(Dispatchers.IO).launch {
            recipe = recipeRepository.getFullRecipeById(recipeId) ?: RecipeWithIngredients.Empty()

            _uiState.update {
                it.copy(
                    allIngredients = ingredientRepository.getAllIngredients(),
                    recipeName = recipe.recipe.name,
                    recipeDescription = recipe.recipe.description,
                    recipeTags = recipe.recipe.tags,
                    recipeIngredients = recipe.ingredients,
                    recipeSteps = recipe.recipe.steps
                )
            }
        }
    }

    override fun onUiEvent(event: EditUiEvent) {
        when (event) {
            EditUiEvent.OnBack, EditUiEvent.OnDiscardRecipe -> navEvent(EditNavEvent.Back)

            is EditUiEvent.OnUpdateRecipeName ->
                _uiState.update { it.copy(recipeName = event.name) }

            is EditUiEvent.OnUpdateRecipeDescription ->
                _uiState.update { it.copy(recipeDescription = event.description) }

            is EditUiEvent.OnUpdateRecipeTags -> {
                val updatedTags = uiState.value.recipeTags.toMutableList()
                if (event.selected) updatedTags.add(event.tag)
                else updatedTags.remove(event.tag)
                _uiState.update { it.copy(recipeTags = updatedTags) }
            }

            is EditUiEvent.OnCreateIngredient -> {
                CoroutineScope(Dispatchers.IO).launch {
                    ingredientRepository.insertIngredient(event.ingredient)
                }
            }

            is EditUiEvent.OnAddIngredientToRecipe -> {
                val updatedIngredients = uiState.value.recipeIngredients.toMutableList()
                updatedIngredients.add(event.recipeIngredient)
                _uiState.update { it.copy(recipeIngredients = updatedIngredients) }
            }

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
                val recipe = Recipe(
                    id = if (recipeId == -1L) 0 else recipeId,
                    name = uiState.value.recipeName,
                    description = uiState.value.recipeDescription,
                    tags = uiState.value.recipeTags,
                    source = Source.Manual,
                    steps = uiState.value.recipeSteps
                )

                val originalIngredients = this.recipe.ingredients
                val currentIngredients = uiState.value.recipeIngredients
                val addedIngredients = currentIngredients.filter { it !in originalIngredients }
                val deletedIngredients = originalIngredients.filter { it !in currentIngredients }

                CoroutineScope(Dispatchers.IO).launch {
                    if (recipeId == -1L) {
                        recipe.id = recipeRepository.insertRecipe(recipe)
                        recipeRepository.insertRecipeIngredients(
                            recipe,
                            uiState.value.recipeIngredients
                        )
                    } else {
                        recipeRepository.updateRecipe(recipe)
                    }

                    deletedIngredients.forEach {
                        recipeRepository.deleteRecipeIngredient(
                            recipe,
                            it
                        )
                    }
                    recipeRepository.insertRecipeIngredients(recipe, addedIngredients)
                }

                navEvent(EditNavEvent.Back)
            }
        }
    }

    override fun defaultUiState(): EditUiState = EditUiState()

    @AssistedFactory
    interface Factory {
        fun create(navEvent: (EditNavEvent) -> Unit, recipeId: Long): EditViewModel
    }
}