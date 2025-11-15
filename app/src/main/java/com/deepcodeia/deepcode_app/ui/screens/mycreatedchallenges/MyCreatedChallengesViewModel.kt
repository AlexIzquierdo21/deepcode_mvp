package com.deepcodeia.deepcode_app.ui.screens.mycreatedchallenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepcodeia.deepcode_app.domain.usecase.challenge.DeleteChallengeUseCase
import com.deepcodeia.deepcode_app.domain.usecase.challenge.GetMyCreatedChallengesUseCase
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
 * ViewModel de la pantalla de mis retos creados.
 * Permite ver y eliminar los retos creados por el usuario.
 */
@HiltViewModel
class MyCreatedChallengesViewModel @Inject constructor(
    private val getMyCreatedChallengesUseCase: GetMyCreatedChallengesUseCase,
    private val deleteChallengeUseCase: DeleteChallengeUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MyCreatedChallengesUiState(isLoading = true))
    val state: StateFlow<MyCreatedChallengesUiState> = _state.asStateFlow()

    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        loadMyChallenges()
    }

    /**
     * Carga los retos creados por el usuario desde el backend.
     */
    private fun loadMyChallenges() = viewModelScope.launch {
        _state.value = _state.value.copy(isLoading = true)

        val result = getMyCreatedChallengesUseCase()

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
            _events.send(UiEvent.ShowSnackbar("Error al cargar tus retos"))
        }
    }

    /**
     * Elimina un reto.
     */
    fun onDeleteChallenge(challengeId: Long) = viewModelScope.launch {
        val result = deleteChallengeUseCase(challengeId)

        if (result.isSuccess) {
            _events.send(UiEvent.ShowSnackbar("Reto eliminado correctamente"))
            // Recargar la lista
            loadMyChallenges()
        } else {
            _events.send(UiEvent.ShowSnackbar("Error al eliminar el reto"))
        }
    }
}