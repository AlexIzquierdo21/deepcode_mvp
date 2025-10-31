package com.deepcodeia.deepcode_app.ui.screens.auth.register

/**
 * Estado inmutable de la pantalla de Registro.
 */
data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val name: String = "",
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val repeatPasswordError: String? = null,
    val nameError: String? = null,
    val submitError: String? = null
) {
    /**
     * El formulario es válido si todos los campos están completos
     * y no hay errores de validación.
     */
    val isValid: Boolean
        get() = email.isNotBlank() &&
                password.isNotBlank() &&
                repeatPassword.isNotBlank() &&
                name.isNotBlank() &&
                emailError == null &&
                passwordError == null &&
                repeatPasswordError == null &&
                nameError == null
}