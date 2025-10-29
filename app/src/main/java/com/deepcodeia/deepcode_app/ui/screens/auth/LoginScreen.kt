package com.deepcodeia.deepcode_app.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deepcodeia.deepcode_app.R
import androidx.compose.material3.Divider
import com.deepcodeia.deepcode_app.ui.components.AppButton
import com.deepcodeia.deepcode_app.ui.components.ButtonVariant

// Pantalla de inicio de sesión de la aplicación
@Composable
fun LoginScreen(onLogin: () -> Unit = {}) {
    // Fondo principal
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            // Contenedor principal centrado
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .widthIn(max = 360.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo de la app
                Image(
                    painter = painterResource(R.drawable.logo_sinfondo),
                    contentDescription = "Logo DeepCodeApp",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(220.dp)
                        .padding(bottom = 8.dp),
                    contentScale = ContentScale.Fit
                )

                // Título de la app
                Text(
                    text = "DeepCodeApp",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(28.dp))

                // Campos de entrada: email y contraseña
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }

                // Campo de correo electrónico
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Correo electrónico") },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        focusedLabelColor = MaterialTheme.colorScheme.primary,
                        focusedLeadingIconColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    )
                )

                Spacer(Modifier.height(14.dp))

                // Campo de contraseña
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Contraseña") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(), // Oculta el texto
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        focusedLabelColor = MaterialTheme.colorScheme.primary,
                        focusedLeadingIconColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    )
                )

                Spacer(Modifier.height(20.dp))

                // Botón principal de inicio de sesión
                AppButton(
                    text = "Iniciar sesión",
                    onClick = onLogin,
                    modifier = Modifier.fillMaxWidth(),
                    variant = ButtonVariant.Primary,
                    textColor = Color.Black
                )

                Spacer(Modifier.height(18.dp))

                // Línea divisoria y texto "O inicia sesión con"
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(Modifier.weight(1f), color = MaterialTheme.colorScheme.outline)
                    Text(
                        "  O Inicia Sesión con  ",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 13.sp
                    )
                    Divider(Modifier.weight(1f), color = MaterialTheme.colorScheme.outline)
                }

                Spacer(Modifier.height(14.dp))

                // Botones sociales (Google y Facebook)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    SocialCircle(iconRes = R.drawable.ic_google)
                    SocialCircle(iconRes = R.drawable.ic_facebook)
                }

                Spacer(Modifier.height(18.dp))

                // Enlace de registro
                Text(
                    text = "¿No tienes cuenta? Regístrate aquí",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// Componente circular para botones sociales (Google/Facebook)
@Composable
private fun SocialCircle(iconRes: Int) {
    Surface(
        shape = CircleShape,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp,
        shadowElevation = 6.dp
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .border(1.dp, MaterialTheme.colorScheme.outline, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
