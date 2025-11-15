package com.deepcodeia.deepcode_app.ui.screens.completedchallenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepcodeia.deepcode_app.domain.usecase.challenge.GetChallengesUseCase
import com.deepcodeia.deepcode_app.domain.usecase.user.GetUserProgressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel de la pantalla de retos completados.
 * Carga todos los retos y filtra solo los completados por el usuario.
 */
@HiltViewModel
class CompletedChallengesViewModel @Inject constructor(
    private val getChallengesUseCase: GetChallengesUseCase,
    private val getUserProgressUseCase: GetUserProgressUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CompletedChallengesUiState(isLoading = true))
    val state: StateFlow<CompletedChallengesUiState> = _state.asStateFlow()

    init {
        loadCompletedChallenges()
    }

    /**
     * Carga los retos completados desde el backend.
     */
    private fun loadCompletedChallenges() = viewModelScope.launch {
        _state.value = _state.value.copy(isLoading = true)

        // Obtener todos los retos
        val challengesResult = getChallengesUseCase()

        // Obtener progreso del usuario
        val progressResult = getUserProgressUseCase()

        if (challengesResult.isSuccess && progressResult.isSuccess) {
            val allChallenges = challengesResult.getOrNull() ?: emptyList()
            val progress = progressResult.getOrNull() ?: emptyList()

            // Filtrar solo los IDs completados
            val completedIds = progress
                .filter { it.status == "COMPLETED" }
                .map { it.challengeId }
                .toSet()

            // Filtrar solo los retos completados
            val completedChallenges = allChallenges.filter {
                completedIds.contains(it.id)
            }

            _state.value = _state.value.copy(
                challenges = completedChallenges,
                isLoading = false
            )
        } else {
            _state.value = _state.value.copy(
                challenges = emptyList(),
                isLoading = false
            )
        }
    }

    /**
     * Filtra por lenguaje de programación.
     */
    fun onLanguageSelected(language: String?) {
        _state.value = _state.value.copy(selectedLanguage = language)
    }

    /**
     * Filtra por nivel de dificultad.
     */
    fun onLevelSelected(level: String?) {
        _state.value = _state.value.copy(selectedLevel = level)
    }
}