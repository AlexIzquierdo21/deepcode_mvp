package com.deepcodeia.deepcode_app.ui.screens.challenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepcodeia.deepcode_app.domain.usecase.challenge.GetChallengesUseCase
import com.deepcodeia.deepcode_app.domain.usecase.challenge.MarkChallengeAsCompletedUseCase
import com.deepcodeia.deepcode_app.domain.usecase.user.GetUserProgressUseCase
import com.deepcodeia.deepcode_app.ui.screens.challenges.CompletionFilter
import com.deepcodeia.deepcode_app.navigation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.delay

/**
 * ViewModel de la pantalla de lista de retos.
 * Conectado al backend mediante GetChallengesUseCase.
 */
@HiltViewModel
class ChallengesViewModel @Inject constructor(
    private val getChallengesUseCase: GetChallengesUseCase,
    private val markChallengeAsCompletedUseCase: MarkChallengeAsCompletedUseCase,
    private val getUserProgressUseCase: GetUserProgressUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ChallengesUiState(isLoading = true))
    val state: StateFlow<ChallengesUiState> = _state.asStateFlow()

    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        loadChallengesWithFilters() // Usa esta función en vez de la otra
        loadUserProgress()
    }

    private fun loadUserProgress() = viewModelScope.launch {
        val progressResult = getUserProgressUseCase()

        if (progressResult.isSuccess) {
            val progress = progressResult.getOrNull() ?: emptyList()
            val completedIds = progress
                .filter { it.status == "COMPLETED" }
                .map { it.challengeId }
                .toSet()

            _state.value = _state.value.copy(completedChallengeIds = completedIds)
        }
    }

    /**
     * Carga los retos y el progreso del usuario desde el backend.
     */
    private fun loadChallengesAndProgress() = viewModelScope.launch {
        _state.value = _state.value.copy(isLoading = true)

        // Cargar retos CON los filtros actuales (null = todos)
        val challengesResult = getChallengesUseCase(
            language = _state.value.selectedLanguage,
            level = _state.value.selectedLevel
        )

        // Cargar progreso del usuario
        val progressResult = getUserProgressUseCase()

        if (challengesResult.isSuccess && progressResult.isSuccess) {
            val challenges = challengesResult.getOrNull() ?: emptyList()
            val progress = progressResult.getOrNull() ?: emptyList()

            val completedIds = progress
                .filter { it.status == "COMPLETED" }
                .map { it.challengeId }
                .toSet()


            _state.value = _state.value.copy(
                challenges = challenges,
                completedChallengeIds = completedIds,
                isLoading = false
            )


        } else {
            // Error al cargar
            _state.value = _state.value.copy(
                challenges = emptyList(),
                completedChallengeIds = emptySet(),
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
     * Marca un reto como completado.
     * Actualiza el progreso del usuario en el backend.
     */
    fun onMarkAsCompleted(challengeId: Long) = viewModelScope.launch {
        val result = markChallengeAsCompletedUseCase(challengeId)

        if (result.isSuccess) {
            // Añadir el ID a la lista de completados
            _state.value = _state.value.copy(
                completedChallengeIds = _state.value.completedChallengeIds + challengeId
            )
            _events.send(UiEvent.ShowSnackbar("¡Reto marcado como completado!"))
        } else {
            val errorMessage = result.exceptionOrNull()?.message ?: "Error al marcar el reto"
            _events.send(UiEvent.ShowSnackbar(errorMessage))
        }
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
            // Mantener los IDs de completados al filtrar
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

    /**
     * Cambia el filtro de completados/no completados.
     */
    fun onCompletionFilterSelected(filter: CompletionFilter) {
        _state.value = _state.value.copy(selectedCompletionFilter = filter)
    }
}