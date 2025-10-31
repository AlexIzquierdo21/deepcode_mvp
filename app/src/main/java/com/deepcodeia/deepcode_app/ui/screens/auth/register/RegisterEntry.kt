package com.deepcodeia.deepcode_app.ui.screens.auth

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.deepcodeia.deepcode_app.navigation.HandleNavigationEvents
import com.deepcodeia.deepcode_app.ui.screens.auth.register.RegisterViewModel

/**
 * Punto de entrada para la pantalla de Registro.
 * Conecta el ViewModel con la UI y maneja los eventos de navegación.
 */
@Composable
fun RegisterEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
) {
    val viewModel: RegisterViewModel = hiltViewModel()

    HandleNavigationEvents(navController, snackbarHostState, viewModel.events)

    val state by viewModel.state.collectAsState()

    RegisterScreen(
        state = state,
        onNameChange = viewModel::onNameChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onRepeatPasswordChange = viewModel::onRepeatPasswordChange,
        onRegisterClick = viewModel::onRegisterClick,
        onBackClick = viewModel::onBackClick
    )
}

