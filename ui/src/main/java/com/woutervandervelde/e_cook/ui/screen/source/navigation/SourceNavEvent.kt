package com.woutervandervelde.e_cook.ui.screen.source.navigation

import com.woutervandervelde.e_cook.ui.navigation.BaseNavEvent

interface SourceNavEvent : BaseNavEvent {
    data class ToRecipe(val recipeId: Long) : SourceNavEvent
}