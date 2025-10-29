package com.deepcodeia.deepcode_app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.deepcodeia.deepcode_app.navigation.navGraph.authGraph
import com.deepcodeia.deepcode_app.navigation.navGraph.homeGraph

/**
 * NavHost principal de la aplicación.
 * Integra los diferentes grafos de navegación (auth, home, etc.)
 */
@Composable
fun AppNavGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = "auth" // Empieza en el flujo de autenticación
    ) {
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


