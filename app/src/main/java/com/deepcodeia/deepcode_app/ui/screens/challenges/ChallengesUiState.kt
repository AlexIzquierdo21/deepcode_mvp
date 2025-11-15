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
    val selectedLevel: String? = null,
    val selectedCompletionFilter: CompletionFilter = CompletionFilter.ALL
) {
    /**
     * Lista de retos filtrada por estado de completado.
     */
    val filteredChallenges: List<Challenge>
        get() = challenges.filter { challenge ->
            when (selectedCompletionFilter) {
                CompletionFilter.ALL -> true
                CompletionFilter.COMPLETED -> isChallengeCompleted(challenge.id)
                CompletionFilter.NOT_COMPLETED -> !isChallengeCompleted(challenge.id)
            }
        }

    /**
     * Verifica si un reto está completado.
     */
    fun isChallengeCompleted(challengeId: Long): Boolean {
        return completedChallengeIds.contains(challengeId)
    }
}

/**
 * Filtro por estado de completado.
 */
enum class CompletionFilter {
    ALL,           // Todos los retos
    COMPLETED,     // Solo completados
    NOT_COMPLETED  // Solo no completados
}