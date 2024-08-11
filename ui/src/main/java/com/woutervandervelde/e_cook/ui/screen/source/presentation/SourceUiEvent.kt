package com.woutervandervelde.e_cook.ui.screen.source.presentation

import com.woutervandervelde.e_cook.domain.model.InstagramVideoInfo
import com.woutervandervelde.e_cook.ui.viewmodel.BaseUiEvent

interface SourceUiEvent : BaseUiEvent {
    data class OnInstagramConvertClick(val videoInfo: InstagramVideoInfo) : SourceUiEvent
}