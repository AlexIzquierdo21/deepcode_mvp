package com.deepcodeia.deepcode_app.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.deepcodeia.deepcode_app.R

/**
 * SplashScreen (UI pura)
 *
 * Pantalla inicial de carga que se muestra mientras se verifica
 * si el usuario tiene una sesión activa (token JWT guardado).
 *
 * Funcionalidad:
 * - Muestra el logo de la app y un indicador de carga
 * - Escucha los cambios en hasToken mediante LaunchedEffect
 * - Navega automáticamente según el resultado:
 *   * true → Home (hay sesión activa)
 *   * false → Login (no hay sesión)
 *   * null → Sigue cargando (no hace nada)
 *
 * Diseño:
 * - Logo centrado de 200dp
 * - CircularProgressIndicator en color primario
 * - Espaciado vertical de 24dp entre elementos
 *
 * @param onNavigateToAuth Callback ejecutado cuando no hay token (ir a Login)
 * @param onNavigateToHome Callback ejecutado cuando hay token válido (ir a Home)
 * @param hasToken Estado actual de verificación del token (true/false/null)
 */
@Composable
fun SplashScreen(
    onNavigateToAuth: () -> Unit,
    onNavigateToHome: () -> Unit,
    hasToken: Boolean?
) {
    /**
     * LaunchedEffect se ejecuta cuando hasToken cambia.
     * Es un efecto secundario que maneja la navegación automática.
     *
     * Flujo:
     * 1. ViewModel verifica si hay token
     * 2. hasToken cambia de null a true/false
     * 3. Este LaunchedEffect detecta el cambio
     * 4. Ejecuta la navegación correspondiente
     */
    LaunchedEffect(hasToken) {
        when (hasToken) {
            true -> onNavigateToHome()   // Hay sesión activa → Home
            false -> onNavigateToAuth()  // No hay sesión → Login
            null -> { /* Todavía verificando, no hacer nada */ }
        }
    }

    // UI de la pantalla de carga
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Logo de la aplicación
                Image(
                    painter = painterResource(R.drawable.logo_sinfondo),
                    contentDescription = "Logo DeepCodeApp",
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Fit
                )

                // Indicador de carga giratorio
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
