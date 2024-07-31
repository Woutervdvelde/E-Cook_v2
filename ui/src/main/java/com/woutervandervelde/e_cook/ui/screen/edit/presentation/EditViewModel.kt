package com.woutervandervelde.e_cook.ui.screen.edit.presentation

import com.woutervandervelde.e_cook.domain.repository.IngredientRepository
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
    ingredientRepository: IngredientRepository,
    @Assisted private val navEvent: (EditNavEvent) -> Unit,
) : BaseViewModel<EditUiState, EditUiEvent>() {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            _uiState.update {
                it.copy(
                    allIngredients = ingredientRepository.getAllIngredients()
                )
            }
        }
    }

    override fun onUiEvent(event: EditUiEvent) {
        when (event) {
            is EditUiEvent.OnBack -> navEvent(EditNavEvent.Back)
        }
    }

    override fun defaultUiState(): EditUiState = EditUiState()

    @AssistedFactory
    interface Factory {
        fun create(navEvent: (EditNavEvent) -> Unit): EditViewModel
    }
}