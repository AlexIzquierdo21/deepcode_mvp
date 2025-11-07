package com.deepcodeia.deepcode_app.ui.screens.profile

/**
 * Estado de la pantalla de Perfil.
 */
data class ProfileUiState(
    val username: String = "",
    val email: String = "",
    val completedChallenges: Int = 0,
    val totalChallenges: Int = 0,
    val isLoading: Boolean = false
) {
    val progressPercentage: Float
        get() = if (totalChallenges > 0) {
            completedChallenges.toFloat() / totalChallenges.toFloat()
        } else 0f
}
