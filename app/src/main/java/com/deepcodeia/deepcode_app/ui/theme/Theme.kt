package com.deepcodeia.deepcode_app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DeepColorScheme = darkColorScheme(
    primary = DeepNeon,
    onPrimary = Color.White,
    secondary = DeepElectricBlue,
    onSecondary = Color.Black,
    background = DeepBg,
    onBackground = Color(0xFFEFEFEF),
    surface = DeepSurface,
    onSurface = Color(0xFFEFEFEF),
    surfaceVariant = Color(0xFF202020),
    onSurfaceVariant = DeepTextSecondary,
    outline = DeepBorder,
)

@Composable
fun DeepCode_AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DeepColorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}
