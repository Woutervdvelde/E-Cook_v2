package com.woutervandervelde.e_cook.ui.screen.home.navigation

import com.woutervandervelde.e_cook.ui.navigation.BaseNavEvent

interface HomeNavEvent : BaseNavEvent {
    data object ToNewRecipe : HomeNavEvent
    data class ToEditRecipe(val id: Long) : HomeNavEvent
}