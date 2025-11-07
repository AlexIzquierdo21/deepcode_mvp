package com.deepcodeia.deepcode_app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.deepcodeia.deepcode_app.navigation.navGraph.authGraph
import com.deepcodeia.deepcode_app.navigation.navGraph.homeGraph
import com.deepcodeia.deepcode_app.ui.screens.splash.SplashEntry

/**
 * NavHost principal de la aplicación.
 * Ahora comienza en Splash para verificar si hay sesión activa.
 */
@Composable
fun AppNavGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Route.Splash.route  // ← Ahora empieza en Splash
    ) {
        // Pantalla de Splash (verificación de token)
        composable(Route.Splash.route) {
            SplashEntry(
                onNavigateToAuth = {
                    navController.navigate("auth") {
                        popUpTo(Route.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate("home_flow") {
                        popUpTo(Route.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        // Grafo de autenticación (Login, Register, etc.)
        authGraph(
            navController = navController,
            snackbarHostState = snackbarHostState,
            contentPadding = contentPadding
        )

        // Grafo principal (Home, Videos, etc.)
        homeGraph(
            navController = navController,
            snackbarHostState = snackbarHostState,
            contentPadding = contentPadding
        )
    }
}

