package com.deepcodeia.deepcode_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.deepcodeia.deepcode_app.ui.screens.HomeScreen
import com.deepcodeia.deepcode_app.ui.screens.LoginScreen
import com.deepcodeia.deepcode_app.ui.screens.VideosScreen

// Define la navegación principal de la app
@Composable
fun AppNavGraph(nav: NavHostController) {

    // Contenedor principal de rutas y pantallas
    NavHost(navController = nav, startDestination = "login") {

        // Ruta de Login
        composable("login") {
            LoginScreen(
                onLogin = {
                    // Al hacer login, navega a "home" y elimina "login" del stack
                    nav.navigate("home") {
                        popUpTo("login") { inclusive = true } // Evita volver atrás al login
                    }
                }
            )
        }

        // Ruta del Home (pantalla principal)
        composable("home") {
            HomeScreen(
                onVideosClick = { nav.navigate("videos") }, // Ir a pantalla de vídeos
                onChallengesClick = { /* nav.navigate("challenges") */ }, // Pendiente de implementar
                onCreateChallengeClick = { /* nav.navigate("createChallenge") */ }, // Pendiente
                onProfileClick = { /* nav.navigate("profile") */ }, // Pendiente
                onLogout = {
                    // Cierra sesión y vuelve al login
                    nav.navigate("login") {
                        popUpTo("home") { inclusive = true } // Limpia el stack
                    }
                }
            )
        }

        // Ruta de la pantalla de vídeos
        composable("videos") {
            VideosScreen(
                playlistId = "PLDkQmEYGZru8fgGR9JM7Lp-BSKD8Xz9-G",          // Playlist principal
                secondaryPlaylistId = "PLDkQmEYGZru-z9VtbV89b-myzSymIGEvg", // Playlist secundaria
                onBack = { nav.popBackStack() } // Volver a la pantalla anterior
            )
        }
    }
}



