package com.woutervandervelde.e_cook.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.woutervandervelde.e_cook.ui.theme.Size0
import com.woutervandervelde.e_cook.ui.theme.Size28
import com.woutervandervelde.e_cook.ui.theme.Size8

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0f, 0f, 0f, 0f),
                        MaterialTheme.colorScheme.background
                    )
                )
            )
    ) {
        Spacer(modifier = Modifier.safeDrawingPadding())
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = Size0, vertical = Size8)
        ) {
            content()
        }
    }
}

@Composable
fun BottomNavigationItem(
    name: String,
    icon: Painter,
    selected: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier
                .size(Size28),
            painter = icon,
            tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
            contentDescription = name
        )
    }
}