package com.deepcodeia.deepcode_app.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepcodeia.deepcode_app.data.LoginDataStore
import com.deepcodeia.deepcode_app.domain.usecase.challenge.GetChallengesUseCase
import com.deepcodeia.deepcode_app.domain.usecase.user.GetCurrentUserUseCase
import com.deepcodeia.deepcode_app.domain.usecase.user.GetUserProgressUseCase
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
 * Obtiene datos reales del backend: información del usuario y progreso de retos.
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val dataStore: LoginDataStore,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getUserProgressUseCase: GetUserProgressUseCase,
    private val getChallengesUseCase: GetChallengesUseCase
) : ViewModel() {

    // Estado inmutable que consume la UI
    private val _state = MutableStateFlow(ProfileUiState(isLoading = true))
    val state: StateFlow<ProfileUiState> = _state.asStateFlow()

    // Canal de eventos de UI
    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        loadUserData()
    }

    /**
     * Carga los datos del usuario y su progreso desde el backend.
     */
    private fun loadUserData() = viewModelScope.launch {
        _state.value = _state.value.copy(isLoading = true)

        // Obtener información del usuario
        val userResult = getCurrentUserUseCase()

        // Obtener progreso de retos
        val progressResult = getUserProgressUseCase()

        // Obtener TODOS los retos de la app
        val allChallengesResult = getChallengesUseCase()

        // Procesar resultados
        if (userResult.isSuccess && progressResult.isSuccess && allChallengesResult.isSuccess) {
            val user = userResult.getOrNull()!!
            val progressList = progressResult.getOrNull()!!
            val allChallenges = allChallengesResult.getOrNull()!!

            // Calcular estadísticas
            val completed = progressList.count { it.status == "COMPLETED" }
            val total = allChallenges.size  // ← Total de retos en la app

            _state.value = ProfileUiState(
                username = user.name ?: "Usuario",
                email = user.email,
                completedChallenges = completed,
                totalChallenges = total,
                isLoading = false
            )
        } else {
            // Error al cargar datos - mostrar datos vacíos
            _state.value = ProfileUiState(
                username = "Error",
                email = "No se pudo cargar",
                completedChallenges = 0,
                totalChallenges = 0,
                isLoading = false
            )
        }
    }
    /**
     * Cierra sesión: limpia el DataStore y navega a Login.
     */
    fun onLogoutClick() = viewModelScope.launch {
        dataStore.clearData()

        _events.send(
            UiEvent.Navigate(
                route = "auth",
                popUpTo = "home_flow",
                inclusive = true
            )
        )
    }
}