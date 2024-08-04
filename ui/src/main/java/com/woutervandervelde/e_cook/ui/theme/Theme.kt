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
    onSecondary = White,
    primaryContainer = White,
    onPrimaryContainer = Black,
    secondaryContainer = Grey,
    onSecondaryContainer = White,
    tertiary = Yellow,
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