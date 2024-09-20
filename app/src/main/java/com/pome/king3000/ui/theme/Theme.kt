package com.pome.king3000.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val DarkColorScheme = darkColorScheme(
    primary = King3000Pinky,
    background = King3000Black,
    surface = King3000DarkGray,
    secondary = King3000White,
    tertiary = King3000White,
    primaryContainer = King3000Pinky30,
    onPrimary = King3000Black,
    onBackground = King3000White,
    onSurface = King3000White,
    onSurfaceVariant = King3000Gray,
    error = King3000DarkRed,
    errorContainer = King3000DarkRed5
)


@Composable
fun King3000Theme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}