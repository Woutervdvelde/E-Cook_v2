package com.woutervandervelde.e_cook.ui.screen.source

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import coil.compose.AsyncImage
import com.woutervandervelde.e_cook.domain.model.InstagramVideoInfo
import com.woutervandervelde.e_cook.ui.R
import com.woutervandervelde.e_cook.ui.component.Button
import com.woutervandervelde.e_cook.ui.screen.source.presentation.SourceState
import com.woutervandervelde.e_cook.ui.screen.source.presentation.SourceUiEvent
import com.woutervandervelde.e_cook.ui.screen.source.presentation.SourceUiState
import com.woutervandervelde.e_cook.ui.theme.Size16
import com.woutervandervelde.e_cook.ui.theme.Size360
import com.woutervandervelde.e_cook.ui.theme.Size4
import com.woutervandervelde.e_cook.ui.theme.Size8

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
            when (uiState.sourceState) {
                SourceState.LOADING, SourceState.CONFIRM ->
                    InstagramNewSource(
                        state = uiState.sourceState,
                        videoInfo = uiState.instagramVideoInfo,
                        onConvertClick = {
                            uiEvent(SourceUiEvent.OnInstagramConvertClick(uiState.instagramVideoInfo!!))
                        }
                    )

                SourceState.PROCESSING -> ProcessingInstagramRecipe()
                SourceState.FINISHED -> {
                    Text(text = "done")
                }

                SourceState.ERROR -> Text(text = stringResource(R.string.source_loaded_error))

                else -> Unit
            }

        }
    }
}


@Composable
fun InstagramNewSource(
    state: SourceState,
    videoInfo: InstagramVideoInfo? = null,
    onConvertClick: () -> Unit
) {
    if (state == SourceState.LOADING) {
        CircularProgressIndicator()
    } else if (state == SourceState.CONFIRM && videoInfo != null) {
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
    }
}

@Composable
fun ProcessingInstagramRecipe() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Size16),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = stringResource(R.string.source_converting_title))
            CircularProgressIndicator()
            Row {
                Text(
                    text = stringResource(R.string.source_converting_powered_by),
                    style = TextStyle()
                )
                Spacer(modifier = Modifier.width(Size4))
                Text(
                    text = stringResource(R.string.source_converting_gemini), style = TextStyle(
                        brush = Brush.linearGradient(
                            listOf(
                                Color(0xFF4285F4),
                                Color(0xFF9B72CB),
                                Color(0xFFD96570),
                                Color(0xFFD96570),
                                Color(0xFF9B72CB),
                                Color(0xFF4285F4),
                                Color(0xFF9B72CB),
                            )
                        )
                    )
                )
            }
        }
    }
}

private const val MAX_TITLE_LENGTH = 20