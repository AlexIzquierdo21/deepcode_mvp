package com.deepcodeia.deepcode_app.ui.screens.challenges

import com.deepcodeia.deepcode_app.domain.model.Challenge

/**
 * Estado de la pantalla de lista de retos.
 * Ahora los filtros se aplican en el backend, no localmente.
 */
data class ChallengesUiState(
    val challenges: List<Challenge> = emptyList(),
    val isLoading: Boolean = false,
    val selectedLanguage: String? = null,
    val selectedLevel: String? = null
) {
    /**
     * Ahora filteredChallenges es simplemente challenges,
     * porque el filtrado se hace en el backend.
     */
    val filteredChallenges: List<Challenge>
        get() = challenges
}