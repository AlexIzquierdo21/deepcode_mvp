package com.deepcodeia.deepcode_app.ui.screens.mycreatedchallenges

import com.deepcodeia.deepcode_app.domain.model.Challenge

/**
 * Estado de la pantalla de mis retos creados.
 */
data class MyCreatedChallengesUiState(
    val challenges: List<Challenge> = emptyList(),
    val isLoading: Boolean = false
)