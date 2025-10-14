package com.deepcodeia.deepcode_app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// Conjunto de formas y bordes redondeados usados en DeepCodeApp
val DeepShapes = Shapes(
    small = RoundedCornerShape(8.dp),    // Esquinas suaves para elementos pequeños (chips, inputs)
    medium = RoundedCornerShape(16.dp),  // Bordes redondeados medios (tarjetas, botones)
    large = RoundedCornerShape(24.dp)    // Bordes amplios para contenedores grandes
)

