package com.woutervandervelde.e_cook.ui.screen.source.presentation

import com.woutervandervelde.e_cook.domain.model.InstagramVideoInfo
import com.woutervandervelde.e_cook.ui.viewmodel.BaseUiState

data class SourceUiState(
    val sourceState: SourceState = SourceState.NONE,
    val processedVideoRecipeId: Long = -1L,
    val instagramVideoInfo: InstagramVideoInfo? = null
): BaseUiState()

enum class SourceState {
    LOADING, CONFIRM, PROCESSING, FINISHED, NONE, ERROR
}