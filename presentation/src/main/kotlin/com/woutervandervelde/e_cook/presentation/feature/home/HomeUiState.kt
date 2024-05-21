package com.woutervandervelde.e_cook.presentation.feature.home

import androidx.compose.runtime.Immutable
import com.woutervandervelde.e_cook.presentation.BaseUiState

@Immutable
data class HomeUiState(
    val placeholder: Boolean = true
): BaseUiState()