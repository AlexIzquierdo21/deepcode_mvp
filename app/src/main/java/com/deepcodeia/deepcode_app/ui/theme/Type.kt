package com.deepcodeia.deepcode_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Tipografía personalizada para DeepCodeApp
val DeepTypography = Typography(
    // Estilo para títulos grandes
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,      // Texto en negrita
        fontSize = 24.sp,                  // Tamaño grande
        color = DeepNeon                   // Color principal (verde neón)
    ),

    // Estilo para texto principal o párrafos
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,    // Peso normal
        fontSize = 16.sp,                  // Tamaño estándar
        color = DeepOnBackground           // Color del texto sobre el fondo
    )
)
