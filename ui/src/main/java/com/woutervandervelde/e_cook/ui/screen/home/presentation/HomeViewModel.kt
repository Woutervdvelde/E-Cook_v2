package com.woutervandervelde.e_cook.ui.screen.home.presentation

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
    @Assisted private val navEvent: (HomeNavEvent) -> Unit
) : BaseViewModel<HomeUiState, HomeUiEvent>() {
    init {
        loadRecipes()
    }

    override fun onUiEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.OnAddRecipeClick -> navEvent(HomeNavEvent.ToNewRecipe)
        }
    }

    fun loadRecipes() {
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