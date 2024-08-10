package com.woutervandervelde.e_cook.ui.screen.source.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.woutervandervelde.e_cook.domain.model.InstagramVideoInfo
import com.woutervandervelde.e_cook.domain.repository.InstagramRepository
import com.woutervandervelde.e_cook.ui.screen.source.navigation.SourceNavEvent
import com.woutervandervelde.e_cook.ui.viewmodel.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel(assistedFactory = SourceViewModel.Factory::class)
class SourceViewModel @AssistedInject constructor(
    @Assisted private val navEvent: (SourceNavEvent) -> Unit,
    @Assisted private val sharedContent: String?,
    private val instagramRepository: InstagramRepository
) : BaseViewModel<SourceUiState, SourceUiEvent>() {

    init {
        if (sharedContent?.isNotBlank() == true) {
            if (sharedContent.contains(INSTAGRAM_PREFIX)) {
                _uiState.update { it.copy(loadingSource = true) }
                viewModelScope.launch {
                    val result = instagramRepository.getVideoInfo(sharedContent)
                    result.onSuccess {videoInfo ->
                        _uiState.update { it.copy(instagramVideoInfo = videoInfo, loadingSource = false) }
                    }
                    result.onFailure {
                        _uiState.update { it.copy(loadingSource = false, loadedSourceError = true) }
                    }
                }
            }
        }
    }

    override fun onUiEvent(event: SourceUiEvent) {
//        TODO("Not yet implemented")
    }

    override fun defaultUiState(): SourceUiState = SourceUiState()

    @AssistedFactory
    interface Factory {
        fun create(
            navEvent: (SourceNavEvent) -> Unit,
            sharedContent: String?
        ): SourceViewModel
    }

    companion object {
        val INSTAGRAM_PREFIX = Regex("instagram.com/reel/|instagram.com/p/")
    }
}