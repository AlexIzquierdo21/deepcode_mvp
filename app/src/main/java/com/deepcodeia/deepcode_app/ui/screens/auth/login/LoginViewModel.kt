package com.deepcodeia.deepcode_app.ui.screens.auth.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepcodeia.deepcode_app.domain.usecase.auth.LoginUseCase
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
 * ViewModel de la pantalla de Login.
 * Gestiona el estado y la lógica de inicio de sesión.
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    // Estado inmutable que consume la UI
    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state.asStateFlow()

    // Canal de eventos de UI (navegación, snackbars)
    private val _events = Channel<UiEvent>(Channel.Factory.BUFFERED)
    val events = _events.receiveAsFlow()

    /**
     * Email cambia: actualiza estado y valida
     */
    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(
            email = email,
            emailError = validateEmail(email),
            submitError = null
        )
    }

    /**
     * Password cambia: actualiza estado y valida
     */
    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(
            password = password,
            passwordError = validatePassword(password),
            submitError = null
        )
    }

    /**
     * Intento de login
     */
    fun onLoginClick() = viewModelScope.launch {
        val s = _state.value

        // Revalidar todo antes de enviar
        val emailErr = validateEmail(s.email)
        val passErr = validatePassword(s.password)

        if (emailErr != null || passErr != null) {
            _state.value = s.copy(
                emailError = emailErr,
                passwordError = passErr
            )
            return@launch
        }

        // Activar loading
        _state.value = s.copy(isLoading = true, submitError = null)

        // Ejecutar caso de uso
        val result = loginUseCase(s.email, s.password)

        _state.value = _state.value.copy(isLoading = false)

        result.onSuccess {
            // Navegar a Home y limpiar el stack de auth
            _events.send(
                UiEvent.Navigate(
                    route = Route.Home.route,
                    popUpTo = "auth",
                    inclusive = true
                )
            )
        }.onFailure { ex ->
            _state.value = _state.value.copy(
                submitError = ex.message ?: "Error al iniciar sesión"
            )
        }
    }

    fun onRegisterClick() = viewModelScope.launch {
        _events.send(UiEvent.Navigate(Route.Register.route))
    }

    // Validaciones
    private fun validateEmail(email: String): String? =
        when {
            email.isBlank() -> "El email es obligatorio"
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Formato de email no válido"
            else -> null
        }

    private fun validatePassword(password: String): String? =
        when {
            password.isBlank() -> "La contraseña es obligatoria"
            password.length < 6 -> "Mínimo 6 caracteres"
            else -> null
        }
}