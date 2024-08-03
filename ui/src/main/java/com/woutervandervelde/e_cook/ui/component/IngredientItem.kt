package com.woutervandervelde.e_cook.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.woutervandervelde.e_cook.domain.model.RecipeIngredient

@Composable
fun IngredientItem(recipeIngredient: RecipeIngredient) {
    Row {
        Text(text = recipeIngredient.ingredient.name)
    }
}