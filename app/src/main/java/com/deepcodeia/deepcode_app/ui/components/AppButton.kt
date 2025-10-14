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


enum class ButtonVariant { Primary, Secondary, Black, Outline }

@Composable
fun AppButton(
    text: String,                          // Texto dentro del botón
    onClick: () -> Unit,                   // Acción que se ejecuta al pulsar
    modifier: Modifier = Modifier,         // Permite pasar modificadores externos
    variant: ButtonVariant = ButtonVariant.Primary, // Tipo de botón
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,      // Icono opcional antes del texto
    textColor: Color? = null,              // Color del texto (override)
) {
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
        ButtonVariant.Outline -> ButtonDefaults.buttonColors(             // Botón con borde y fondo transparente
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    }

    val shape = MaterialTheme.shapes.medium   // Forma del botón (usa la del tema)

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
            if (leadingIcon != null) {
                Icon(leadingIcon, null)
                Spacer(Modifier.width(8.dp))
            }
            Text(
                text,
                fontWeight = FontWeight.SemiBold,
                color = textColor ?: LocalContentColor.current
            )
        }
    } else {
        Button(
            onClick = onClick,
            enabled = enabled,
            modifier = modifier.height(Dimens.ButtonHeight),
            shape = shape,
            contentPadding = PaddingValues(horizontal = 16.dp),
            colors = btnColors
        ) {
            if (leadingIcon != null) {
                Icon(leadingIcon, null)
                Spacer(Modifier.width(8.dp))
            }
            Text(
                text,
                fontWeight = FontWeight.SemiBold,
                color = textColor ?: LocalContentColor.current   // ← override si se pasa
            )
        }
    }
}
