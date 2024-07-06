package com.woutervandervelde.e_cook.ui.screen.edit.presentation

import android.content.Context
import androidx.compose.ui.res.stringResource
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.ui.R
import com.woutervandervelde.e_cook.ui.viewmodel.BaseUiState

data class EditUiState(
    val recipe: Recipe
) : BaseUiState()
