package com.deepcodeia.deepcode_app.ui.screens.challenges

import com.deepcodeia.deepcode_app.domain.model.Challenge

/**
 * Estado de la pantalla de lista de retos.
 */
data class ChallengesUiState(
    val challenges: List<Challenge> = emptyList(),
    val isLoading: Boolean = false,
    val selectedLanguage: String? = null,  // null = todos los lenguajes
    val selectedLevel: String? = null       // null = todos los niveles
) {
    /**
     * Lista de retos filtrada según los filtros seleccionados.
     */
    val filteredChallenges: List<Challenge>
        get() = challenges.filter { challenge ->
            val matchesLanguage = selectedLanguage == null ||
                    challenge.programmingLanguage == selectedLanguage
            val matchesLevel = selectedLevel == null ||
                    challenge.level == selectedLevel
            matchesLanguage && matchesLevel
        }
}