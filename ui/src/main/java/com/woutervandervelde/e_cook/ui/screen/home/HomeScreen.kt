package com.woutervandervelde.e_cook.ui.screen.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.woutervandervelde.e_cook.domain.BuildConfig
import com.woutervandervelde.e_cook.domain.model.Recipe
import com.woutervandervelde.e_cook.ui.R
import com.woutervandervelde.e_cook.ui.component.IconButton
import com.woutervandervelde.e_cook.ui.component.Tag
import com.woutervandervelde.e_cook.ui.screen.home.presentation.HomeUiEvent
import com.woutervandervelde.e_cook.ui.screen.home.presentation.HomeUiState
import com.woutervandervelde.e_cook.ui.theme.Size128
import com.woutervandervelde.e_cook.ui.theme.Size16
import com.woutervandervelde.e_cook.ui.theme.Size32
import com.woutervandervelde.e_cook.ui.theme.Size360
import com.woutervandervelde.e_cook.ui.theme.Size4
import com.woutervandervelde.e_cook.ui.theme.Size56
import com.woutervandervelde.e_cook.ui.theme.Size64
import com.woutervandervelde.e_cook.ui.theme.Size8

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun HomeScreen(
    uiState: HomeUiState,
    uiEvent: (HomeUiEvent) -> Unit
) {
    Column {
        Header(
            onNewRecipe = { uiEvent(HomeUiEvent.OnAddRecipeClick) },
            onRandomRecipe = { uiEvent(HomeUiEvent.OnRandomRecipeClick) }
        )

        if (uiState.recipes.isEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(R.string.home_empty_recipe_explenation), textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(Size32))
                val gifEnabledLoader = ImageLoader.Builder(LocalContext.current)
                    .components { add(ImageDecoderDecoder.Factory()) }.build()
                AsyncImage(
                    model = R.raw.ecook_instagram_example,
                    imageLoader = gifEnabledLoader,
                    contentDescription = null
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(bottom = Size64),
            horizontalArrangement = Arrangement.spacedBy(Size8),
            verticalArrangement = Arrangement.spacedBy(Size8),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = Size16)
        ) {
            items(uiState.recipes) {
                RecipeCard(
                    it,
                    onClick = { uiEvent(HomeUiEvent.OnRecipeClick(it.id)) }
                )
            }
        }
    }
}

@Composable
private fun Header(onNewRecipe: () -> Unit, onRandomRecipe: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(Size360)
    ) {
        Image(
            painterResource(id = R.drawable.header_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .safeDrawingPadding()
                .padding(horizontal = Size16)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painterResource(
                        id = R.drawable.ecook_logo
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = Size16)
                        .width(Size128)
                        .height(Size56)
                )
            }
            Text(
                text = stringResource(R.string.home_header_slogan),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = Size32)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Size16)
            ) {
                IconButton(
                    text = stringResource(R.string.button_add_recipe),
                    icon = painterResource(id = R.drawable.add),
                    onClick = onNewRecipe,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(Size16))
                IconButton(
                    text = stringResource(R.string.button_random_recipe),
                    icon = painterResource(id = R.drawable.dice_5),
                    onClick = onRandomRecipe,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun RecipeCard(recipe: Recipe, onClick: () -> Unit) {
    Card(
        modifier = Modifier
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