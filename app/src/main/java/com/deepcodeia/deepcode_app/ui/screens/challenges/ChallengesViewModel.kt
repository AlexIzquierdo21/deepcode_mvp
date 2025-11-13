package com.deepcodeia.deepcode_app.ui.screens.challenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepcodeia.deepcode_app.domain.usecase.challenge.GetChallengesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel de la pantalla de lista de retos.
 * Conectado al backend mediante GetChallengesUseCase.
 */
@HiltViewModel
class ChallengesViewModel @Inject constructor(
    private val getChallengesUseCase: GetChallengesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ChallengesUiState(isLoading = true))
    val state: StateFlow<ChallengesUiState> = _state.asStateFlow()

    init {
        loadChallenges()
    }

    /**
     * Carga los retos desde el backend.
     */
    private fun loadChallenges() = viewModelScope.launch {
        _state.value = _state.value.copy(isLoading = true)

        val result = getChallengesUseCase()

        if (result.isSuccess) {
            val challenges = result.getOrNull() ?: emptyList()
            _state.value = _state.value.copy(
                challenges = challenges,
                isLoading = false
            )
        } else {
            // Error al cargar
            _state.value = _state.value.copy(
                challenges = emptyList(),
                isLoading = false
            )
        }
    }

    /**
     * Filtra por lenguaje de programación.
     * Recarga los retos del backend con el filtro.
     */
    fun onLanguageSelected(language: String?) {
        _state.value = _state.value.copy(selectedLanguage = language)
        loadChallengesWithFilters()
    }

    /**
     * Filtra por nivel de dificultad.
     * Recarga los retos del backend con el filtro.
     */
    fun onLevelSelected(level: String?) {
        _state.value = _state.value.copy(selectedLevel = level)
        loadChallengesWithFilters()
    }

    /**
     * Recarga los retos aplicando los filtros actuales.
     */
    private fun loadChallengesWithFilters() = viewModelScope.launch {
        _state.value = _state.value.copy(isLoading = true)

        val result = getChallengesUseCase(
            language = _state.value.selectedLanguage,
            level = _state.value.selectedLevel
        )

        if (result.isSuccess) {
            val challenges = result.getOrNull() ?: emptyList()
            _state.value = _state.value.copy(
                challenges = challenges,
                isLoading = false
            )
        } else {
            _state.value = _state.value.copy(
                challenges = emptyList(),
                isLoading = false
            )
        }
    }
}