package com.deepcodeia.deepcode_app.ui.screens.challenges

import com.deepcodeia.deepcode_app.domain.model.Challenge

/**
 * Estado de la pantalla de lista de retos.
 * Ahora incluye información sobre qué retos están completados.
 */
data class ChallengesUiState(
    val challenges: List<Challenge> = emptyList(),
    val completedChallengeIds: Set<Long> = emptySet(),  // IDs de retos completados
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

    /**
     * Verifica si un reto está completado.
     */
    fun isChallengeCompleted(challengeId: Long): Boolean {
        return completedChallengeIds.contains(challengeId)
    }
}