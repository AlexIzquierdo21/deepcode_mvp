package com.deepcodeia.deepcode_app.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * SplashEntry
 *
 * Punto de entrada para la pantalla de Splash.
 *
 * Responsabilidades:
 * - Inyecta el SplashViewModel usando Hilt
 * - Observa el estado hasToken del ViewModel
 * - Conecta el ViewModel con la UI (SplashScreen)
 * - Recibe callbacks de navegación desde el NavGraph
 *
 * Patrón Entry:
 * Este composable actúa como "conector" entre:
 * - NavGraph (proporciona callbacks de navegación)
 * - ViewModel (proporciona lógica y estado)
 * - Screen (UI pura que solo dibuja)
 *
 * @param onNavigateToAuth Callback para navegar al flujo de autenticación (Login)
 * @param onNavigateToHome Callback para navegar al flujo principal (Home)
 */
@Composable
fun SplashEntry(
    onNavigateToAuth: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    // Inyectar ViewModel automáticamente con Hilt
    val viewModel: SplashViewModel = hiltViewModel()

    // Observar el estado hasToken como Compose State
    // Se recompone automáticamente cuando cambia
    val hasToken by viewModel.hasToken.collectAsState()

    // Renderizar la UI pasándole el estado y los callbacks
    SplashScreen(
        onNavigateToAuth = onNavigateToAuth,
        onNavigateToHome = onNavigateToHome,
        hasToken = hasToken
    )
}