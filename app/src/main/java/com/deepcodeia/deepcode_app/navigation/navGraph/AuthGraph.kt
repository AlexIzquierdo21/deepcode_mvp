package com.deepcodeia.deepcode_app.navigation.navGraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.deepcodeia.deepcode_app.navigation.Route
import com.deepcodeia.deepcode_app.ui.screens.auth.RegisterEntry
import com.deepcodeia.deepcode_app.ui.screens.auth.login.LoginEntry

/**
 * Grafo de navegación para el flujo de autenticación.
 */
fun NavGraphBuilder.authGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
) {
    navigation(
        startDestination = Route.Login.route,
        route = "auth"
    ) {
        // Pantalla de Login
        composable(Route.Login.route) {
            LoginEntry(
                navController = navController,
                snackbarHostState = snackbarHostState,
                contentPadding = contentPadding
            )
        }

        // Pantalla de Registro
        composable(Route.Register.route) {
            RegisterEntry(
                navController = navController,
                snackbarHostState = snackbarHostState,
                contentPadding = contentPadding
            )
        }

        // TODO: Añadir RecoverPassword cuando esté creada
    }
}
