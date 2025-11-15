package com.deepcodeia.deepcode_app.ui.screens.createchallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepcodeia.deepcode_app.domain.usecase.challenge.CreateChallengeUseCase
import com.deepcodeia.deepcode_app.navigation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel de la pantalla de creación de retos.
 * Conectado al backend mediante CreateChallengeUseCase.
 */
@HiltViewModel
class CreateChallengeViewModel @Inject constructor(
    private val createChallengeUseCase: CreateChallengeUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CreateChallengeUiState())
    val state: StateFlow<CreateChallengeUiState> = _state.asStateFlow()

    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    /**
     * Actualiza el título del reto.
     */
    fun onTitleChange(title: String) {
        _state.value = _state.value.copy(
            title = title,
            titleError = if (title.isBlank()) "El título no puede estar vacío" else null
        )
    }

    /**
     * Actualiza la descripción del reto.
     */
    fun onDescriptionChange(description: String) {
        _state.value = _state.value.copy(
            description = description,
            descriptionError = if (description.isBlank()) "La descripción no puede estar vacía" else null
        )
    }

    /**
     * Selecciona el lenguaje de programación.
     */
    fun onLanguageSelected(language: String) {
        _state.value = _state.value.copy(selectedLanguage = language)
    }

    /**
     * Selecciona el nivel de dificultad.
     */
    fun onLevelSelected(level: String) {
        _state.value = _state.value.copy(selectedLevel = level)
    }

    /**
     * Crea el reto en el backend.
     */
    fun onCreateClick() = viewModelScope.launch {
        // Validar campos
        if (!_state.value.isValid) {
            _events.send(UiEvent.ShowSnackbar("Por favor completa todos los campos"))
            return@launch
        }

        _state.value = _state.value.copy(isLoading = true)

        // Llamar al backend
        val result = createChallengeUseCase(
            title = _state.value.title,
            description = _state.value.description,
            language = _state.value.selectedLanguage,
            level = _state.value.selectedLevel
        )

        _state.value = _state.value.copy(isLoading = false)

        if (result.isSuccess) {
            _events.send(UiEvent.ShowSnackbar("¡Reto creado exitosamente!"))
            _events.send(UiEvent.NavigateBack)
        } else {
            _events.send(UiEvent.ShowSnackbar("Error al crear el reto"))
        }
    }
}

















