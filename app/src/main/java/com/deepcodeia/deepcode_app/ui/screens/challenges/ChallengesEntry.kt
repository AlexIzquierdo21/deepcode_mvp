package com.deepcodeia.deepcode_app.ui.screens.challenges

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

/**
 * Punto de entrada para la pantalla de lista de retos.
 * Conecta el ViewModel con la UI.
 */
@Composable
fun ChallengesEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
) {
    val viewModel: ChallengesViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    ChallengesScreen(
        state = state,
        onLanguageSelected = viewModel::onLanguageSelected,
        onLevelSelected = viewModel::onLevelSelected,
        onChallengeClick = { challenge ->
            // TODO: Navegar a detalle del reto
            // navController.navigate("challenge_detail/${challenge.id}")
        },
        onBack = { navController.popBackStack() }

        /**
         * Anidar en un try-catch??; ha funcionado una vez de 100
         *
         *  try {
         *      nav.Controller.popBackStack()
         *  } catch (e: Exception) {
         *      println("Error al navegar: ${e.message}
         *
         * ====== HE PROBADO ====== APARECE MENSAJE...
         * Log.d("DEBUG: Botón volver pulsado")
         *     navController.popBackStack()
         */
    )
}
