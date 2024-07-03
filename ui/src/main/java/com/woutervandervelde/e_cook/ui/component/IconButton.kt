package com.woutervandervelde.e_cook.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.woutervandervelde.e_cook.ui.theme.Size10
import com.woutervandervelde.e_cook.ui.theme.Size32
import com.woutervandervelde.e_cook.ui.theme.Size8

@Composable
internal fun IconButton(
    text: String,
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        shape = RoundedCornerShape(Size10),
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(painter = icon, contentDescription = null)
        Spacer(modifier = Modifier.width(Size8))
        Text(text = text, style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(vertical = Size8))
    }
}