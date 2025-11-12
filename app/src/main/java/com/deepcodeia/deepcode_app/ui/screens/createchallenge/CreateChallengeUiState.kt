package com.deepcodeia.deepcode_app.ui.screens.createchallenge

/**
 * Estado de la pantalla de creación de retos
 */
data class CreateChallengeUiState(
    val title: String = "",
    val description: String = "",
    val selectedLanguage: String = "PYTHON",
    val selectedLevel: String = "BEGINNER",
    val isLoading: Boolean = false,

    // Errores de validación
    val titleError: String? = null,
    val descriptionError: String? = null
) {
    /**
     * Verifica si el formulario es válido.
     */
    val isValid: Boolean
        get() = title.isNotBlank() &&
                description.isNotBlank() &&
                titleError == null &&
                descriptionError == null
}


