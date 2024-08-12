package com.woutervandervelde.e_cook.ui.screen.recipe

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.IconButton as M3IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.woutervandervelde.e_cook.ui.R
import com.woutervandervelde.e_cook.ui.screen.recipe.presentation.RecipeUiEvent
import com.woutervandervelde.e_cook.ui.screen.recipe.presentation.RecipeUiState
import com.woutervandervelde.e_cook.ui.theme.Size10
import com.woutervandervelde.e_cook.ui.theme.Size128
import com.woutervandervelde.e_cook.ui.theme.Size16
import com.woutervandervelde.e_cook.ui.theme.Size32
import com.woutervandervelde.e_cook.ui.theme.Size48
import com.woutervandervelde.e_cook.ui.theme.Size8

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun RecipeScreen(
    uiState: RecipeUiState,
    uiEvent: (RecipeUiEvent) -> Unit
) {
    Scaffold(
        topBar = {
            RecipeTopBar(
                onBack = { uiEvent(RecipeUiEvent.OnBackClick) },
                onBookmark = { uiEvent(RecipeUiEvent.OnBookmarkClick) },
                onEdit = { uiEvent(RecipeUiEvent.OnEditClick) }
            )
        }
    ) {
        Column {
            RecipeHeader(uiState.recipe.image ?: "", uiState.recipe.name)
        }
    }
}

@Composable
fun RecipeHeader(image: String, name: String) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(.9f)
        ) {
            AsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Size128)
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colorStops = arrayOf(
                                0.0f to Color.Transparent,
                                1.1f to Color.Black
                            )
                        )
                    )
            )

            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxHeight()
                    .safeDrawingPadding()
                    .padding(horizontal = Size16)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}

@Composable
fun RecipeTopBar(onBack: () -> Unit, onBookmark: () -> Unit, onEdit: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .safeDrawingPadding()
            .padding(horizontal = Size16)
            .fillMaxWidth()
            .height(Size32)
    ) {
        Column {
            M3IconButton(onClick = onBack) {
                Icon(painterResource(R.drawable.arrow_back), contentDescription = null)
            }
        }
        Row {
            M3IconButton(
                onClick = onBookmark,
                modifier = Modifier
                    .background(Color.Gray.copy(alpha = .75f), RoundedCornerShape(Size10))
                    .aspectRatio(1f)
            ) {
                Icon(painterResource(R.drawable.bookmark_add), contentDescription = null)
            }
            Spacer(modifier = Modifier.width(Size8))
            M3IconButton(
                onClick = onEdit,
                modifier = Modifier
                    .background(Color.Gray.copy(alpha = .75f), RoundedCornerShape(Size10))
                    .aspectRatio(1f)
            ) {

                Icon(painterResource(R.drawable.edit), contentDescription = null)
            }
        }
    }
}
