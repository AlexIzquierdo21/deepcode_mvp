package com.deepcodeia.deepcode_app.ui.screens.createchallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
 * Por ahora usa datos fake, luego se conectará al backend.
 */

@HiltViewModel
class CreateChallengeViewModel @Inject constructor(
    // TODO: Inyectar CreateChallengeUseCase cuando exista
) : ViewModel() {

    private val _state = MutableStateFlow(
        CreateChallengeUiState())
    val state: StateFlow<CreateChallengeUiState> = _state.asStateFlow()

    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    /**
     * Actualiza el titulo del reto
     */
    fun onTitleChange(title: String) {
        _state.value = _state.value.copy(
            title = title,
            titleError = if(title.isBlank()) "El título no puede estar vacío" else null
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
     * Selecciona el lenguaje de programación
     */
    fun onLanguageSelected(language: String) {
        _state.value = _state.value.copy(selectedLanguage = language)
    }

    /**
     * Selecciona el nivel de dificultad
     */
    fun onLevelSelected(level: String) {
        _state.value = _state.value.copy(selectedLevel = level)
    }

    /**
     * Intenta crear el reto
     * TODO = Conectar el backend con POST /challenges
     */
    fun onCreateClick() = viewModelScope.launch {
        // Validar Campos
        if (!_state.value.isValid) {
            _events.send(UiEvent.ShowSnackbar("Por favor completa todos los campos"))
            return@launch
        }

        _state.value = _state.value.copy(isLoading = true)

        // TODO = Llamar al BackEnd
        // val result = CreateChallengeUseCase(...)

        //Simulación de éxito
        kotlinx.coroutines.delay(1000)

        _state.value = _state.value.copy(isLoading = false)
        _events.send(UiEvent.ShowSnackbar("¡Reto Creado exitosamente!"))
        _events.send(UiEvent.NavigateBack)
    }
}

















