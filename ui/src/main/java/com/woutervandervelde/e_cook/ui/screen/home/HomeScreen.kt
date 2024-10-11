package com.woutervandervelde.e_cook.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.ui.R
import com.woutervandervelde.e_cook.ui.component.IconButton
import com.woutervandervelde.e_cook.ui.screen.home.components.Header
import com.woutervandervelde.e_cook.ui.screen.home.components.NoRecipesExplanation
import com.woutervandervelde.e_cook.ui.screen.home.components.RecipeCard
import com.woutervandervelde.e_cook.ui.screen.home.presentation.HomeUiEvent
import com.woutervandervelde.e_cook.ui.screen.home.presentation.HomeUiState
import com.woutervandervelde.e_cook.ui.theme.Size0
import com.woutervandervelde.e_cook.ui.theme.Size128
import com.woutervandervelde.e_cook.ui.theme.Size16
import com.woutervandervelde.e_cook.ui.theme.Size32
import com.woutervandervelde.e_cook.ui.theme.Size360
import com.woutervandervelde.e_cook.ui.theme.Size56
import com.woutervandervelde.e_cook.ui.theme.Size64
import com.woutervandervelde.e_cook.ui.theme.Size8

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    uiEvent: (HomeUiEvent) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(vertical = Size8),
        verticalArrangement = Arrangement.spacedBy(Size8),
        horizontalArrangement = Arrangement.spacedBy(Size8),
        modifier = Modifier.fillMaxSize()
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Header(
                onNewRecipe = { uiEvent(HomeUiEvent.OnAddRecipeClick) },
                onRandomRecipe = { uiEvent(HomeUiEvent.OnRandomRecipeClick) }
            )
        }

        // Show an empty state message if there are no recipes
        if (uiState.recipes.isEmpty()) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                NoRecipesExplanation()
            }
        } else {
            itemsIndexed(uiState.recipes) { index, recipe ->
                val left = index % 2 == 0

                RecipeCard(
                    recipe = recipe,
                    onClick = { uiEvent(HomeUiEvent.OnRecipeClick(recipe.id)) },
                    modifier = Modifier.padding(
                        start = if (left) Size16 else Size0,
                        end = if (!left) Size16 else Size0
                    )
                )
            }
        }
        item(span = { GridItemSpan(maxLineSpan) }) { Spacer(modifier = Modifier.height(Size64)) }
    }
}




