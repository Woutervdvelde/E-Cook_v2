package com.woutervandervelde.e_cook.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<UI: BaseUiState, UE> : ViewModel() {
    protected val _uiState by lazy { MutableStateFlow(defaultUiState()) }
    val uiState: StateFlow<UI> = _uiState.asStateFlow()

    abstract fun defaultUiState(): UI
    abstract fun onUiEvent(event: UE)
}