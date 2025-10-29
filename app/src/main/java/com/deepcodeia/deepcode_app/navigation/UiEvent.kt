package com.deepcodeia.deepcode_app.navigation

/**
 * Eventos que el ViewModel emite hacia la UI para:
 * - Navegar a otras pantallas
 * - Volver atrás
 * - Mostrar mensajes (Snackbar)
 */
sealed class UiEvent {

    /** Navegar a una ruta específica */
    data class Navigate(
        val route: String,
        val popUpTo: String? = null,
        val inclusive: Boolean = false,
        val singleTop: Boolean = true
    ) : UiEvent()

    /** Volver a la pantalla anterior */
    data object NavigateBack : UiEvent()

    /** Mostrar mensaje temporal */
    data class ShowSnackbar(val message: String) : UiEvent()
}