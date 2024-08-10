package com.woutervandervelde.e_cook.ui.screen.source.presentation

import com.woutervandervelde.e_cook.domain.model.InstagramVideoInfo
import com.woutervandervelde.e_cook.ui.viewmodel.BaseUiState

data class SourceUiState(
    val loadingSource: Boolean = false,
    val loadedSourceError: Boolean = false,
    val instagramVideoInfo: InstagramVideoInfo? = null
): BaseUiState()