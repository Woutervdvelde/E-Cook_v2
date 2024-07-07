package com.woutervandervelde.e_cook.ui.screen.edit.presentation

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.ui.R
import com.woutervandervelde.e_cook.ui.screen.edit.navigation.EditNavEvent
import com.woutervandervelde.e_cook.ui.screen.home.navigation.HomeNavEvent
import com.woutervandervelde.e_cook.ui.screen.home.presentation.HomeViewModel
import com.woutervandervelde.e_cook.ui.viewmodel.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext

@HiltViewModel(assistedFactory = EditViewModel.Factory::class)
class EditViewModel @AssistedInject constructor(
    @Assisted private val navEvent: (EditNavEvent) -> Unit,
) : BaseViewModel<EditUiState, EditUiEvent>() {

    init {
    }

    override fun onUiEvent(event: EditUiEvent) {
        when (event) {
            is EditUiEvent.OnBack -> navEvent(EditNavEvent.Back)
        }
    }

    override fun defaultUiState(): EditUiState = EditUiState(
        recipe = Recipe.Empty()
    )

    @AssistedFactory
    interface Factory {
        fun create(navEvent: (EditNavEvent) -> Unit): EditViewModel
    }
}