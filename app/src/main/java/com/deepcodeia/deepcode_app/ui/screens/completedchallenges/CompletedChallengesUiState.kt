package com.deepcodeia.deepcode_app.ui.screens.completedchallenges

import com.deepcodeia.deepcode_app.domain.model.Challenge

/**
 * Estado de la pantalla de retos completados.
 */
data class CompletedChallengesUiState(
    val challenges: List<Challenge> = emptyList(),
    val isLoading: Boolean = false,
    val selectedLanguage: String? = null,
    val selectedLevel: String? = null
) {
    /**
     * Lista de retos completados filtrada localmente.
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