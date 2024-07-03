package com.woutervandervelde.e_cook.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    background = Black,
    onBackground = White,
    primary = White,
    onPrimary = Black,
    secondary = Grey,
    primaryContainer = White,
    onPrimaryContainer = Black,
)

@Composable
fun EcookTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}