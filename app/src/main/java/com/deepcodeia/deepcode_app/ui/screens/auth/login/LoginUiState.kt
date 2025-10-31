package com.deepcodeia.deepcode_app.ui.screens.auth.login

/**
 * Estado inmutable de la pantalla de Login.
 * Contiene todos los datos que la UI necesita para renderizarse.
 */
data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val submitError: String? = null
) {
    /**
     * El formulario es válido si:
     * - Los campos no están vacíos
     * - No hay errores de validación
     */
    val isValid: Boolean
        get() = email.isNotBlank() &&
                password.isNotBlank() &&
                emailError == null &&
                passwordError == null
}