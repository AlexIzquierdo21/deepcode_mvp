package com.deepcodeia.deepcode_app.ui.screens.createchallenge

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.deepcodeia.deepcode_app.navigation.HandleNavigationEvents

/**
 * Punto de entrada para la pantalla de creación de retos
 * Conecta ViewModel con UI
 */

@Composable
fun CreateChallengeEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
) {
    val viewModel: CreateChallengeViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    // Manejar eventos de navegación
    HandleNavigationEvents(navController, snackbarHostState, viewModel.events)

    CreateChallengeScreen(
        state = state,
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onLanguageSelected = viewModel::onLanguageSelected,
        onLevelSelected = viewModel::onLevelSelected,
        onCreateClick = viewModel::onCreateClick,
        onBack = { navController.popBackStack() }
    )
}



























