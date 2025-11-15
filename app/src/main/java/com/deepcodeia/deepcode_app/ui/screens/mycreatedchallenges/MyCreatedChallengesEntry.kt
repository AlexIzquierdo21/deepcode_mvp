package com.deepcodeia.deepcode_app.ui.screens.mycreatedchallenges

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.deepcodeia.deepcode_app.navigation.HandleNavigationEvents

/**
 * Punto de entrada para la pantalla de mis retos creados.
 * Conecta el ViewModel con la UI.
 */
@Composable
fun MyCreatedChallengesEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
) {
    val viewModel: MyCreatedChallengesViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    // Manejar eventos de navegación
    HandleNavigationEvents(navController, snackbarHostState, viewModel.events)

    MyCreatedChallengesScreen(
        state = state,
        onDeleteChallenge = viewModel::onDeleteChallenge,
        onBack = { navController.navigateUp() }
    )
}