package com.deepcodeia.deepcode_app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

// PUENTE QUE CONECTA EL SISTEMA DE MATERIAL3 (INTEGRACIÓN COMPLETA)
// Esquema de colores principal en modo oscuro para DeepCodeApp
private val DeepColorScheme = darkColorScheme(
    primary = DeepNeon,                  // Color principal (verde neón)
    onPrimary = DeepBg,                  // Color del texto/iconos sobre el color primario
    secondary = DeepElectricBlue,        // Color secundario (azul eléctrico)
    onSecondary = DeepBg,                // Texto/iconos sobre el color secundario
    background = DeepBg,                 // Fondo principal de la app
    onBackground = DeepOnBackground,     // Texto sobre el fondo principal
    surface = DeepSurface,               // Superficies (tarjetas, paneles)
    onSurface = DeepOnBackground,        // Texto sobre las superficies
    surfaceVariant = DeepSurfaceVariant, // Variante más clara de las superficies
    onSurfaceVariant = DeepTextSecondary,// Texto secundario o menos relevante
    outline = DeepBorder                 // Bordes, divisores y contornos
)

// Tema global de la aplicación que unifica colores, tipografía y formas
@Composable
fun DeepCode_AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DeepColorScheme,   // Aplica la paleta de colores definida
        typography = DeepTypography,     // Usa la tipografía personalizada
        shapes = DeepShapes,             // Usa las formas (bordes redondeados) definidas
        content = content                // Contenido envuelto dentro del tema
    )
}



