package com.woutervandervelde.e_cook.ui.screen.edit.navigation

import com.woutervandervelde.e_cook.ui.navigation.BaseNavEvent

sealed interface EditNavEvent : BaseNavEvent {
    data object Back : EditNavEvent
    data object ToHome : EditNavEvent
}