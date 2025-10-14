package com.deepcodeia.deepcode_app.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.deepcodeia.deepcode_app.ui.theme.Dimens
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

// Tipos de botones disponibles en la app
enum class ButtonVariant { Primary, Secondary, Black, Outline }

// Componente de botón reutilizable con diferentes variantes y estilos
@Composable
fun AppButton(
    text: String,                          // Texto mostrado en el botón
    onClick: () -> Unit,                   // Acción al hacer click
    modifier: Modifier = Modifier,         // Modificadores externos
    variant: ButtonVariant = ButtonVariant.Primary, // Tipo de botón (por defecto Primary)
    enabled: Boolean = true,               // Estado habilitado/deshabilitado
    leadingIcon: ImageVector? = null,      // Icono opcional a la izquierda del texto
    textColor: Color? = null,              // Color personalizado del texto
) {
    // Define colores según el tipo de botón
    val btnColors = when (variant) {
        ButtonVariant.Primary -> ButtonDefaults.buttonColors(             // Botón principal (verde neón)
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
        ButtonVariant.Secondary -> ButtonDefaults.buttonColors(           // Botón secundario
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        )
        ButtonVariant.Black -> ButtonDefaults.buttonColors(               // Botón negro sólido
            containerColor = Color.Black,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
        ButtonVariant.Outline -> ButtonDefaults.buttonColors(             // Botón sin fondo (solo borde)
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    }

    // Define la forma del botón (bordes redondeados)
    val shape = RoundedCornerShape(Dimens.ButtonRadius)

    // Si el botón es de tipo Outline (solo borde)
    if (variant == ButtonVariant.Outline) {
        OutlinedButton(
            onClick = onClick,
            enabled = enabled,
            modifier = modifier.height(Dimens.ButtonHeight),
            shape = shape,
            contentPadding = PaddingValues(horizontal = 16.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.onSurface),
            border = ButtonDefaults.outlinedButtonBorder()
        ) {
            // Si tiene icono, lo muestra antes del texto
            if (leadingIcon != null) {
                Icon(leadingIcon, null)
                Spacer(Modifier.width(8.dp))
            }
            // Texto del botón
            Text(
                text,
                fontWeight = FontWeight.SemiBold,
                color = textColor ?: LocalContentColor.current
            )
        }
    }
    // Si el botón es de cualquier otro tipo (Primary, Secondary, Black)
    else {
        Button(
            onClick = onClick,
            enabled = enabled,
            modifier = modifier.height(Dimens.ButtonHeight),
            shape = shape,
            contentPadding = PaddingValues(horizontal = 16.dp),
            colors = btnColors
        ) {
            // Si tiene icono, lo muestra antes del texto
            if (leadingIcon != null) {
                Icon(leadingIcon, null)
                Spacer(Modifier.width(8.dp))
            }
            // Texto del botón
            Text(
                text,
                fontWeight = FontWeight.SemiBold,
                color = textColor ?: LocalContentColor.current   // Usa color custom si se pasa
            )
        }
    }
}
