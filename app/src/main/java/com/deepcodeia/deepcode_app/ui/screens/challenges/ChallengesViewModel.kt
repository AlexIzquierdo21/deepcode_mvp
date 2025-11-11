package com.deepcodeia.deepcode_app.ui.screens.challenges

import androidx.lifecycle.ViewModel
import com.deepcodeia.deepcode_app.domain.model.Challenge
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * ViewModel de la pantalla de lista de retos.
 * Por ahora usa datos fake, luego se conectará al backend.
 */
@HiltViewModel
class ChallengesViewModel @Inject constructor(
    // TODO: Inyectar GetChallengesUseCase cuando exista
) : ViewModel() {

    private val _state = MutableStateFlow(
        ChallengesUiState(
            challenges = getFakeChallenges(),
            isLoading = false
        )
    )
    val state: StateFlow<ChallengesUiState> = _state.asStateFlow()

    /**
     * Filtra por lenguaje de programación.
     * Si es null, muestra todos.
     */
    fun onLanguageSelected(language: String?) {
        _state.value = _state.value.copy(selectedLanguage = language)
    }

    /**
     * Filtra por nivel de dificultad.
     * Si es null, muestra todos.
     */
    fun onLevelSelected(level: String?) {
        _state.value = _state.value.copy(selectedLevel = level)
    }

    /**
     * Datos fake para desarrollo.
     * TODO: Reemplazar con llamada al backend.
     */
    private fun getFakeChallenges(): List<Challenge> {
        return listOf(
            Challenge(
                id = 1,
                title = "Hola Mundo en Python",
                description = "Crea un programa que imprima 'Hola Mundo' en la consola",
                programmingLanguage = "PYTHON",
                level = "BEGINNER",
                createdBy = "Admin",
                createdAt = "2024-11-01"
            ),
            Challenge(
                id = 2,
                title = "Calculadora básica",
                description = "Implementa una calculadora con operaciones básicas",
                programmingLanguage = "JAVA",
                level = "BEGINNER",
                createdBy = "Admin",
                createdAt = "2024-11-02"
            ),
            Challenge(
                id = 3,
                title = "Lista de tareas con Compose",
                description = "Crea una app de tareas usando Jetpack Compose",
                programmingLanguage = "KOTLIN",
                level = "INTERMEDIATE",
                createdBy = "Admin",
                createdAt = "2024-11-03"
            ),
            Challenge(
                id = 4,
                title = "Página web responsive",
                description = "Diseña una landing page responsive con HTML, CSS y JS",
                programmingLanguage = "HTML_CSS_JS",
                level = "BEGINNER",
                createdBy = "Admin",
                createdAt = "2024-11-04"
            ),
            Challenge(
                id = 5,
                title = "Algoritmo de ordenamiento",
                description = "Implementa QuickSort en Python",
                programmingLanguage = "PYTHON",
                level = "INTERMEDIATE",
                createdBy = "Admin",
                createdAt = "2024-11-05"
            )
        )
    }
}