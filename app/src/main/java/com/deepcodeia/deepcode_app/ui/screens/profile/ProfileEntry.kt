package com.deepcodeia.deepcode_app.ui.screens.profile

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.deepcodeia.deepcode_app.navigation.HandleNavigationEvents
import com.deepcodeia.deepcode_app.navigation.Route

/**
 *
 * ProfileEntry
 * ----------------------------------------------------------------------------
 * Punto de entrada para la pantalla de Perfil.
 *
 * Responsabilidades:
 * - Inyecta el ProfileViewModel usando Hilt
 * - Maneja eventos de navegación (especialmente el logout)
 * - Observa el estado del ViewModel
 * - Conecta el ViewModel con la UI (ProfileScreen)
 *
 * Patrón Entry:
 * Actúa como conector entre NavGraph, ViewModel y Screen.
 *
 * @param navController Controlador de navegación
 * @param snackbarHostState Estado del Snackbar para mostrar mensajes
 * @param contentPadding Padding del contenido (del Scaffold)
 */
@Composable
fun ProfileEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
) {
    val viewModel: ProfileViewModel = hiltViewModel()

    HandleNavigationEvents(navController, snackbarHostState, viewModel.events)

    val state by viewModel.state.collectAsState()

    ProfileScreen(
        state = state,
        onMyCreatedChallengesClick = {
            navController.navigate(Route.MyCreatedChallenges.route)
        },
        onLogoutClick = viewModel::onLogoutClick,
        onBack = { navController.popBackStack() }
    )
}
