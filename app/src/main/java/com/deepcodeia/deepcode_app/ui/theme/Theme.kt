package com.deepcodeia.deepcode_app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

// Esquema de colores principal en modo oscuro para DeepCodeApp
private val DeepColorScheme = darkColorScheme(
    primary = DeepNeon,
    onPrimary = DeepBg,
    secondary = DeepElectricBlue,
    onSecondary = DeepBg,
    background = DeepBg,
    onBackground = DeepOnBackground,
    surface = DeepSurface,
    onSurface = DeepOnBackground,
    surfaceVariant = DeepSurfaceVariant,
    onSurfaceVariant = DeepTextSecondary,
    outline = DeepBorder,
    // Colores para snackbar
    inverseSurface = DeepBg,
    inverseOnSurface = DeepNeon,           // ← Texto del Snackbar (negro)
    inversePrimary = DeepElectricBlue,   // ← Color del botón de acción
    // Colores personalizados para retos completados y chips
    tertiary = DeepCompletedChallenge,           // ← Para retos completados
    primaryContainer = DeepChipLanguage,         // ← Para chips de lenguaje
    secondaryContainer = DeepChipLevel           // ← Para chips de nivel
)

// Tema global - SIEMPRE usa el esquema oscuro
@Composable
fun DeepCode_AppTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DeepColorScheme,  // Siempre usa el esquema oscuro
        typography = DeepTypography,
        shapes = DeepShapes,
        content = content
    )
}


