package com.deepcodeia.deepcode_app.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * ProfileScreen (UI pura)
 *
 * Pantalla de perfil del usuario que muestra:
 * - Avatar circular con icono de persona
 * - Nombre de usuario y email
 * - Card con progreso de retos (barra de progreso + estadísticas)
 * - Botón de cerrar sesión
 *
 * No contiene lógica de negocio, solo recibe estado y callbacks.
 *
 * @param state Estado actual del perfil (username, email, progreso)
 * @param onLogoutClick Callback ejecutado al pulsar "Cerrar Sesión"
 */
@Composable
fun ProfileScreen(
    state: ProfileUiState,
    onLogoutClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(40.dp))

            /**
             * Avatar circular del usuario.
             * Fondo en color primario con icono de persona centrado.
             */
            Surface(
                modifier = Modifier.size(120.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Avatar",
                        modifier = Modifier.size(60.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            // Nombre de usuario (del estado)
            Text(
                text = state.username,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(8.dp))

            // Email del usuario (del estado)
            Text(
                text = state.email,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(40.dp))

            /**
             * Card de progreso de retos.
             * Muestra:
             * - Título "Progreso de Retos"
             * - LinearProgressIndicator con porcentaje calculado
             * - Texto "X de Y retos completados"
             */
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Progreso de Retos",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(Modifier.height(16.dp))

                    /**
                     * Barra de progreso lineal.
                     * El progreso se calcula en ProfileUiState.progressPercentage
                     * (completedChallenges / totalChallenges)
                     */
                    LinearProgressIndicator(
                        progress = { state.progressPercentage },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(12.dp),
                        color = MaterialTheme.colorScheme.primary,
                    )

                    Spacer(Modifier.height(12.dp))

                    // Texto descriptivo del progreso
                    Text(
                        text = "${state.completedChallenges} de ${state.totalChallenges} retos completados",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Spacer flexible que empuja el botón hacia abajo
            Spacer(Modifier.weight(1f))

            /**
             * Botón de cerrar sesión.
             * Color rojo (error) para indicar acción destructiva.
             * Al pulsarlo ejecuta onLogoutClick que limpia el token y navega a Login.
             */
            Button(
                onClick = onLogoutClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text("Cerrar Sesión")
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}