package com.woutervandervelde.e_cook.ui.screen.source.presentation

import com.woutervandervelde.e_cook.ui.screen.source.navigation.SourceNavEvent
import com.woutervandervelde.e_cook.ui.viewmodel.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update

@HiltViewModel(assistedFactory = SourceViewModel.Factory::class)
class SourceViewModel @AssistedInject constructor(
    @Assisted private val navEvent: (SourceNavEvent) -> Unit,
    @Assisted private val newInstagramUrl: String?
) : BaseViewModel<SourceUiState, SourceUiEvent>() {

    init {
        _uiState.update { it.copy(temp = newInstagramUrl?:"not provided") }
    }

    override fun onUiEvent(event: SourceUiEvent) {
//        TODO("Not yet implemented")
    }

    override fun defaultUiState(): SourceUiState = SourceUiState()

    @AssistedFactory
    interface Factory {
        fun create(
            navEvent: (SourceNavEvent) -> Unit,
            newInstagramUrl: String?
        ): SourceViewModel
    }
}