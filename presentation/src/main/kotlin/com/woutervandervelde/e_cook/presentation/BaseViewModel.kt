package com.woutervandervelde.e_cook.presentation

import androidx.lifecycle.ViewModel
import com.woutervandervelde.e_cook.presentation.utils.EventFlow
import com.woutervandervelde.e_cook.presentation.utils.MutableEventFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

abstract class BaseViewModel<UI : BaseUiState, EV : BaseUiEvent, NE : BaseNavEvent> : ViewModel(), KoinComponent {
    protected val _uiState by lazy { MutableStateFlow(defaultUiState()) }
    val uiState: StateFlow<UI> = _uiState.asStateFlow()

    protected val _navState by lazy { MutableEventFlow<NE>() }
    val navState: EventFlow<NE> = _navState.asEventFlow()

    abstract fun defaultUiState(): UI
    abstract fun onUiEvent(event: EV)
}