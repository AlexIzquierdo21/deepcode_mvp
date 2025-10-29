package com.deepcodeia.deepcode_app.navigation.navGraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.deepcodeia.deepcode_app.navigation.Route
import com.deepcodeia.deepcode_app.ui.screens.auth.LoginScreen

/**
 * Grafo de navegación para el flujo de autenticación.
 * Contiene: Login, Register (futuro), RecoverPassword (futuro)
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
            LoginScreen(
                onLogin = {
                    navController.navigate(Route.Home.route) {
                        popUpTo("auth") { inclusive = true }
                    }
                }
            )
        }

        // TODO: Añadir Register y RecoverPassword cuando estén creadas
    }
}
