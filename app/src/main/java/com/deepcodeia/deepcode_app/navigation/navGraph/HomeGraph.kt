package com.deepcodeia.deepcode_app.navigation.navGraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.deepcodeia.deepcode_app.navigation.Route
import com.deepcodeia.deepcode_app.ui.screens.home.HomeScreen
import com.deepcodeia.deepcode_app.ui.screens.home.VideosScreen
import com.deepcodeia.deepcode_app.ui.screens.profile.ProfileEntry

/**
 * Grafo de navegación para el flujo principal de la app.
 * Contiene: Home, Videos, Challenges (futuro), CreateChallenge (futuro), Profile (futuro)
 */
fun NavGraphBuilder.homeGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
) {
    navigation(
        startDestination = Route.Home.route,
        route = "home_flow"
    ) {
        // Pantalla principal
        composable(Route.Home.route) {
            HomeScreen(
                onVideosClick = { navController.navigate(Route.Videos.route) },
                onChallengesClick = { /* TODO: navegar cuando exista */ },
                onCreateChallengeClick = { /* TODO: navegar cuando exista */ },
                onProfileClick = { navController.navigate(Route.Profile.route) },
                onLogout = {
                    navController.navigate("auth") {
                        popUpTo("home_flow") { inclusive = true }
                    }
                }
            )
        }

        // Pantalla de Videos
        composable(Route.Videos.route) {
            VideosScreen(
                playlistId = "PLDkQmEYGZru8fgGR9JM7Lp-BSKD8Xz9-G",
                secondaryPlaylistId = "PLDkQmEYGZru-z9VtbV89b-myzSymIGEvg",
                onBack = { navController.popBackStack() }
            )
        }
        // Pantalla de Perfil (NUEVA)
        composable(Route.Profile.route) {
            ProfileEntry(
                navController = navController,
                snackbarHostState = snackbarHostState,
                contentPadding = contentPadding
            )
        }

        // TODO: Añadir Challenges, CreateChallenge cuando estén creadas
    }
}
