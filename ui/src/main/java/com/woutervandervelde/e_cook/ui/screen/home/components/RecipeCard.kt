package com.woutervandervelde.e_cook.ui.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.ui.theme.Size128
import com.woutervandervelde.e_cook.ui.theme.Size16
import com.woutervandervelde.e_cook.ui.theme.Size8

@Composable
internal fun RecipeCard(recipe: Recipe, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(.75f),
        onClick = onClick,
        shape = RoundedCornerShape(Size16),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Background image
            AsyncImage(
                model = recipe.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .height(Size128)
                    .background(
                        Brush.verticalGradient(
                            colorStops = arrayOf(
                                0f to Color.Transparent,
                                1f to Color.Black.copy(alpha = .75f)
                            )
                        )
                    )
            )

            Text(
                text = recipe.name,
                style = MaterialTheme.typography.bodyLarge.copy(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(Size8),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}