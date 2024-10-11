package com.woutervandervelde.e_cook.ui.screen.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import com.woutervandervelde.e_cook.ui.R
import com.woutervandervelde.e_cook.ui.theme.Size32

@Composable
internal fun NoRecipesExplanation() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.home_empty_recipe_explenation),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(Size32))

        // Load and display GIF
        val gifEnabledLoader = ImageLoader.Builder(LocalContext.current)
            .components { add(ImageDecoderDecoder.Factory()) }
            .build()

        AsyncImage(
            model = R.raw.ecook_instagram_example,
            imageLoader = gifEnabledLoader,
            contentDescription = null
        )
    }
}