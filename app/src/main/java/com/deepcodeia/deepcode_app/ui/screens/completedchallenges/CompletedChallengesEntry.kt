package com.deepcodeia.deepcode_app.ui.screens.completedchallenges

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

/**
 * Punto de entrada para la pantalla de retos completados.
 * Conecta el ViewModel con la UI.
 */
@Composable
fun CompletedChallengesEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
) {
    val viewModel: CompletedChallengesViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    CompletedChallengesScreen(
        state = state,
        onLanguageSelected = viewModel::onLanguageSelected,
        onLevelSelected = viewModel::onLevelSelected,
        onChallengeClick = { challenge ->
            // TODO: Navegar a detalle del reto
        },
        onBack = { navController.popBackStack() }
    )
}
