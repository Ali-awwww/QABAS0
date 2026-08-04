package com.qabas.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Dark Color Scheme (افتراضي)
private val DarkColorScheme = darkColorScheme(
    primary = CompassGold,
    secondary = SpaceLight,
    background = SpaceDark,
    surface = SpaceLight,
    onPrimary = TextPrimary,
    onBackground = TextPrimary
)

// Light Color Scheme (للاستخدام المستقبلي)
private val LightColorScheme = lightColorScheme(
    primary = CompassGoldDark,
    background = Color(0xFFF7F4EC),
    onBackground = Color(0xFF1A1A1A)
)

@Composable
fun QabasTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}
