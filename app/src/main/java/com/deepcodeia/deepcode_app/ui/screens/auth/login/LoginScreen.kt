package com.deepcodeia.deepcode_app.ui.screens.auth.login

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
import androidx.compose.runtime.Composable
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
import com.deepcodeia.deepcode_app.ui.components.AppButton
import com.deepcodeia.deepcode_app.ui.components.ButtonVariant
import com.deepcodeia.deepcode_app.ui.screens.auth.login.LoginUiState

/**
 * Pantalla de inicio de sesión (UI pura).
 * No contiene lógica de negocio, solo recibe estado y callbacks.
 */
@Composable
fun LoginScreen(
    state: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () ->Unit
) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
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

                // Campo de correo electrónico
                OutlinedTextField(
                    value = state.email,
                    onValueChange = onEmailChange,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Correo electrónico") },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                    singleLine = true,
                    isError = state.emailError != null,
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

                // Mostrar error de email si existe
                state.emailError?.let { error ->
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 4.dp)
                    )
                }

                Spacer(Modifier.height(14.dp))

                // Campo de contraseña
                OutlinedTextField(
                    value = state.password,
                    onValueChange = onPasswordChange,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Contraseña") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    singleLine = true,
                    isError = state.passwordError != null,
                    visualTransformation = PasswordVisualTransformation(),
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

                // Mostrar error de password si existe
                state.passwordError?.let { error ->
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 4.dp)
                    )
                }

                Spacer(Modifier.height(20.dp))

                // Error general de submit
                state.submitError?.let { error ->
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(12.dp))
                }

                // Botón principal de inicio de sesión
                AppButton(
                    text = if (state.isLoading) "Cargando..." else "Iniciar sesión",
                    onClick = onLoginClick,
                    modifier = Modifier.fillMaxWidth(),
                    variant = ButtonVariant.Primary,
                    textColor = Color.Black,
                    enabled = state.isValid && !state.isLoading
                )

                Spacer(Modifier.height(18.dp))

                // Línea divisoria y texto "O inicia sesión con"
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(Modifier.weight(1f), color = MaterialTheme.colorScheme.outline)
                    Text(
                        "  O Inicia Sesión con  ",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 13.sp
                    )
                    HorizontalDivider(Modifier.weight(1f), color = MaterialTheme.colorScheme.outline)
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
                TextButton(onClick = onRegisterClick) {
                    Text(
                        text = "¿No tienes cuenta? Regístrate aquí",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
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