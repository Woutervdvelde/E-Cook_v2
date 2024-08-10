package com.woutervandervelde.e_cook.ui.screen.source

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.loader.content.Loader
import coil.compose.AsyncImage
import com.woutervandervelde.e_cook.domain.model.InstagramVideoInfo
import com.woutervandervelde.e_cook.ui.R
import com.woutervandervelde.e_cook.ui.component.Button
import com.woutervandervelde.e_cook.ui.screen.home.presentation.HomeUiEvent
import com.woutervandervelde.e_cook.ui.screen.home.presentation.HomeUiState
import com.woutervandervelde.e_cook.ui.screen.source.navigation.SourceNavEvent
import com.woutervandervelde.e_cook.ui.screen.source.presentation.SourceUiEvent
import com.woutervandervelde.e_cook.ui.screen.source.presentation.SourceUiState
import com.woutervandervelde.e_cook.ui.theme.Size16
import com.woutervandervelde.e_cook.ui.theme.Size36
import com.woutervandervelde.e_cook.ui.theme.Size360

@Composable
fun SourceScreen(
    uiState: SourceUiState,
    uiEvent: (SourceUiEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.padding(horizontal = Size16)
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(Size16),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            InstagramNewSource(
                loading = uiState.loadingSource,
                loadError = uiState.loadedSourceError,
                videoInfo = uiState.instagramVideoInfo,
                onConvertClick = {

                }
            )
        }
    }
}


@Composable
fun InstagramNewSource(
    loading: Boolean,
    loadError: Boolean,
    videoInfo: InstagramVideoInfo? = null,
    onConvertClick: () -> Unit
) {
    if (loading) {
        CircularProgressIndicator()
    } else if (videoInfo != null && !loadError) {
        AsyncImage(
            model = videoInfo.thumbnail,
            contentDescription = null,
            modifier = Modifier.height(Size360)
        )
        Text(
            text = if (videoInfo.title.count() + 3 > MAX_TITLE_LENGTH) "${
                videoInfo.title.take(
                    MAX_TITLE_LENGTH
                )
            }..."
            else videoInfo.title,
            style = MaterialTheme.typography.titleSmall
        )
        Button(
            text = stringResource(R.string.source_convert_reel_button),
            onClick = onConvertClick
        )
    } else if (loadError) {
        Text(text = stringResource(R.string.source_loaded_error))
    }
}

private const val MAX_TITLE_LENGTH = 20