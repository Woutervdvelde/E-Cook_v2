package com.woutervandervelde.e_cook.ui.screen.edit.presentation

import com.woutervandervelde.e_cook.ui.viewmodel.BaseUiEvent

interface EditUiEvent : BaseUiEvent {
    data object OnBack: EditUiEvent
}