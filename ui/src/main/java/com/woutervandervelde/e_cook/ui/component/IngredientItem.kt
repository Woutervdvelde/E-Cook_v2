package com.woutervandervelde.e_cook.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.woutervandervelde.e_cook.domain.model.RecipeIngredient
import com.woutervandervelde.e_cook.ui.theme.Size8

@Composable
fun IngredientItem(recipeIngredient: RecipeIngredient) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = recipeIngredient.ingredient.name, style = MaterialTheme.typography.bodyMedium)
        Row {
            Text(text = recipeIngredient.quantity.toString(), style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.width(Size8))
            Text(text = recipeIngredient.unit.text, style = MaterialTheme.typography.bodyMedium)
        }
    }
}