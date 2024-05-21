package com.woutervandervelde.e_cook.presentation.feature.home

import androidx.compose.runtime.Stable
import com.woutervandervelde.e_cook.presentation.BaseViewModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
@Stable
class HomeViewModel : BaseViewModel<HomeUiState, HomeUiEvent, HomeNavEvent>() {
    override fun defaultUiState(): HomeUiState = HomeUiState()

    override fun onUiEvent(event: HomeUiEvent) {
        when(event) {
            else -> Unit
        }
    }
}