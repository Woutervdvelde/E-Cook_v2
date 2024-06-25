package com.woutervandervelde.e_cook.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationBar(content: @Composable RowScope.() -> Unit) {
    Row (
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 8.dp)
    ) {
        content()
    }
}

@Composable
fun BottomNavigationItem(
    name: String,
    icon: Painter,
    onClick: () -> Unit
) {
    Icon(
        modifier = Modifier
            .size(28.dp)
            .clickable { onClick() },
        painter = icon,
        contentDescription = name
    )
}