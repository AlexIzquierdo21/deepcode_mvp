package com.deepcodeia.deepcode_app.ui.screens.auth.login

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.deepcodeia.deepcode_app.navigation.HandleNavigationEvents
import com.deepcodeia.deepcode_app.ui.screens.auth.login.LoginViewModel

/**
 * Punto de entrada para la pantalla de Login.
 * Conecta el ViewModel con la UI y maneja los eventos de navegación.
 */
@Composable
fun LoginEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
) {
    // Inyectar ViewModel con Hilt
    val viewModel: LoginViewModel = hiltViewModel()

    // Manejar eventos de navegación
    HandleNavigationEvents(navController, snackbarHostState, viewModel.events)

    // Observar el estado
    val state by viewModel.state.collectAsState()

    // Renderizar UI con callbacks al ViewModel
    LoginScreen(
        state = state,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = viewModel::onLoginClick,
        onRegisterClick = viewModel::onRegisterClick
    )
}
