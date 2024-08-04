package com.woutervandervelde.e_cook.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.IconButton
import com.woutervandervelde.e_cook.ui.R
import com.woutervandervelde.e_cook.ui.theme.Size200
import com.woutervandervelde.e_cook.ui.theme.Size36
import com.woutervandervelde.e_cook.ui.theme.Size360
import com.woutervandervelde.e_cook.ui.theme.Size8

@Composable
fun RecipeStepCard(step: String, number: Int?, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .defaultMinSize(minHeight = Size200),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier.padding(Size8)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (number == null) "" else "$number.",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                if (number != null) {
                    IconButton(onClick = onDelete) {
                        Icon(
                            painter = painterResource(R.drawable.delete),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
            Text(text = step, color = MaterialTheme.colorScheme.onPrimaryContainer)
        }
    }
}