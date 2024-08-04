package com.woutervandervelde.e_cook.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    modifier: Modifier = Modifier,
    buttonType: ButtonType = ButtonType.PRIMARY
) {
    val foregroundColor = when (buttonType) {
        ButtonType.PRIMARY -> MaterialTheme.colorScheme.onPrimary
        ButtonType.SECONDARY -> MaterialTheme.colorScheme.onSecondary
    }

    val backgroundColor = when (buttonType) {
        ButtonType.PRIMARY -> MaterialTheme.colorScheme.primary
        ButtonType.SECONDARY -> MaterialTheme.colorScheme.secondary
    }

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(Size10),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = foregroundColor
            )
            Spacer(modifier = Modifier.width(Size8))
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(vertical = Size8),
                color = foregroundColor
            )
        }
    }
}

enum class ButtonType {
    PRIMARY, SECONDARY
}