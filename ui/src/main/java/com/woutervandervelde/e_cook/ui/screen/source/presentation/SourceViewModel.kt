package com.woutervandervelde.e_cook.ui.screen.source.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.woutervandervelde.e_cook.domain.model.MeasurementUnit
import com.woutervandervelde.e_cook.domain.repository.InstagramRepository
import com.woutervandervelde.e_cook.domain.repository.VideoRepository
import com.woutervandervelde.e_cook.domain.usecase.ConvertJsonToRecipeIngredientsUseCase
import com.woutervandervelde.e_cook.domain.usecase.ExtractVideoFramesUseCase
import com.woutervandervelde.e_cook.domain.usecase.GetGeminiVideoInfoUseCase
import com.woutervandervelde.e_cook.ui.screen.source.navigation.SourceNavEvent
import com.woutervandervelde.e_cook.ui.viewmodel.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@HiltViewModel(assistedFactory = SourceViewModel.Factory::class)
class SourceViewModel @AssistedInject constructor(
    @Assisted private val navEvent: (SourceNavEvent) -> Unit,
    @Assisted private val sharedContent: String?,
    private val instagramRepository: InstagramRepository,
    private val videoRepository: VideoRepository,
    @ApplicationContext private val context: Context,
    private val convertJsonToRecipeIngredientsUseCase: ConvertJsonToRecipeIngredientsUseCase,
) : BaseViewModel<SourceUiState, SourceUiEvent>() {

    init {
        if (sharedContent?.isNotBlank() == true) {
            if (sharedContent.contains(INSTAGRAM_PREFIX)) {
                val url = "https://instagram.com/reel/${sharedContent.substringAfter("/reel/")}"
                _uiState.update { it.copy(sourceState = SourceState.LOADING) }
                viewModelScope.launch {
                    val result = instagramRepository.getVideoInfo(url)
                    result.onSuccess { videoInfo ->
                        _uiState.update {
                            it.copy(
                                instagramVideoInfo = videoInfo,
                                sourceState = SourceState.CONFIRM
                            )
                        }
                    }
                    result.onFailure {
                        _uiState.update { it.copy(sourceState = SourceState.ERROR) }
                    }
                }
            }
        }
    }

    override fun onUiEvent(event: SourceUiEvent) {
        when (event) {
            is SourceUiEvent.OnInstagramConvertClick -> {
                _uiState.update { it.copy(sourceState = SourceState.PROCESSING) }
                CoroutineScope(Dispatchers.IO).launch {
                    val file = videoRepository.getVideoFromUrl(event.videoInfo.videoUrl)
                    if (file != null) {
                        val images = ExtractVideoFramesUseCase.invoke(file, context)
                        val geminiResponse =
                            GetGeminiVideoInfoUseCase.invoke(event.videoInfo, images)
                        val recipeId = convertJsonToRecipeIngredientsUseCase.invoke(geminiResponse, event.videoInfo)
                        _uiState.update {
                            it.copy(
                                sourceState = SourceState.FINISHED,
                                processedVideoRecipeId = recipeId
                            )
                        }
                    } else {
                        _uiState.update { it.copy(sourceState = SourceState.ERROR) }
                    }
                }
            }

            is SourceUiEvent.OnNavigateToRecipeClick ->
                navEvent(SourceNavEvent.ToRecipe(event.recipeId))
        }
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
        val INSTAGRAM_PREFIX = Regex("instagram.com/(.*/)?reel/")
    }
}