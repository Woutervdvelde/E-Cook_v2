package com.woutervandervelde.e_cook.ui.screen.edit.presentation

import com.woutervandervelde.e_cook.ui.screen.edit.navigation.EditNavEvent
import com.woutervandervelde.e_cook.ui.screen.home.navigation.HomeNavEvent
import com.woutervandervelde.e_cook.ui.screen.home.presentation.HomeViewModel
import com.woutervandervelde.e_cook.ui.viewmodel.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = EditViewModel.Factory::class)
class EditViewModel @AssistedInject constructor(
    @Assisted private val navEvent: (EditNavEvent) -> Unit
) : BaseViewModel<EditUiState, EditUiEvent>() {

    init {

    }

    override fun onUiEvent(event: EditUiEvent) {
        //TODO("Not yet implemented")
    }

    override fun defaultUiState(): EditUiState = EditUiState()

    @AssistedFactory
    interface Factory {
        fun create(navEvent: (EditNavEvent) -> Unit): EditViewModel
    }
}