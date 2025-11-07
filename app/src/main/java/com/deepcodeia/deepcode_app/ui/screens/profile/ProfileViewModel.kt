package com.deepcodeia.deepcode_app.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepcodeia.deepcode_app.data.LoginDataStore
import com.deepcodeia.deepcode_app.navigation.Route
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
 * ViewModel de la pantalla de Perfil.
 * Gestiona la información del usuario y el logout.
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val dataStore: LoginDataStore
) : ViewModel() {

    // Estado inmutable que consume la UI
    private val _state = MutableStateFlow(ProfileUiState(
        username = "Usuario Test",  // TODO: Obtener del backend
        email = "test@deepcode.com", // TODO: Obtener del backend
        completedChallenges = 5,     // TODO: Obtener del backend
        totalChallenges = 20         // TODO: Obtener del backend
    ))
    val state: StateFlow<ProfileUiState> = _state.asStateFlow()

    // Canal de eventos de UI
    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    /**
     * Cierra sesión: limpia el DataStore y navega a Login.
     */
    fun onLogoutClick() = viewModelScope.launch {
        // Limpiar token y datos guardados
        dataStore.clearData()

        // Navegar a auth y limpiar todo el stack
        _events.send(
            UiEvent.Navigate(
                route = "auth",
                popUpTo = "home_flow",
                inclusive = true
            )
        )
    }
}
