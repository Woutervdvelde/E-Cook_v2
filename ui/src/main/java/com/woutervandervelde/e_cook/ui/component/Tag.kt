package com.woutervandervelde.e_cook.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.woutervandervelde.e_cook.ui.theme.Size1
import com.woutervandervelde.e_cook.ui.theme.Size16
import com.woutervandervelde.e_cook.ui.theme.Size8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tag(
    name: String,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onSelectChange: (selected: Boolean) -> Unit = { },
) {
    val backgroundColor by animateColorAsState(
        if (selected) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.secondary.copy(alpha = .25f),
        label = "backgroundColor",
    )

    val foregroundColor by animateColorAsState(
        if (selected) MaterialTheme.colorScheme.onPrimary
        else MaterialTheme.colorScheme.onBackground,
        label = "foregroundColor",
    )

    CompositionLocalProvider(
        LocalMinimumInteractiveComponentEnforcement provides false
    ) {
        Surface(
            color = backgroundColor,
            shape = CircleShape,
            onClick = {
                onSelectChange(!selected)
            },
            modifier = modifier
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.labelMedium,
                color = foregroundColor,
                modifier = Modifier.padding(horizontal = Size16, vertical = Size8)
            )
        }
    }
}