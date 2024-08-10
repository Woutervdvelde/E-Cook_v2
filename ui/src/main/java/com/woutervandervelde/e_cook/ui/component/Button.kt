package com.woutervandervelde.e_cook.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.woutervandervelde.e_cook.ui.theme.Size10
import com.woutervandervelde.e_cook.ui.theme.Size8

@Composable
internal fun Button(
    modifier: Modifier = Modifier,
    text: String? = null,
    onClick: () -> Unit,
    buttonType: ButtonType = ButtonType.PRIMARY,
    content: @Composable() () -> Unit = {}
) {
    val foregroundColor = when (buttonType) {
        ButtonType.PRIMARY -> MaterialTheme.colorScheme.onPrimary
        ButtonType.SECONDARY -> MaterialTheme.colorScheme.onSecondary
    }

    val backgroundColor = when (buttonType) {
        ButtonType.PRIMARY -> MaterialTheme.colorScheme.primary
        ButtonType.SECONDARY -> MaterialTheme.colorScheme.secondary
    }

    androidx.compose.material3.Button(
        onClick = onClick,
        shape = RoundedCornerShape(Size10),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        modifier = modifier
    ) {
        if (text?.isNotBlank() == true) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(vertical = Size8),
                color = foregroundColor
            )
        } else {
            content()
        }
    }
}

enum class ButtonType {
    PRIMARY, SECONDARY
}