package com.deepcodeia.deepcode_app.ui.screens.auth.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepcodeia.deepcode_app.domain.usecase.auth.RegisterUseCase
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
 * ViewModel de la pantalla de Registro.
 * Gestiona el estado y la lógica de registro de nuevos usuarios.
 */
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    // Estado inmutable que consume la UI
    private val _state = MutableStateFlow(RegisterUiState())
    val state: StateFlow<RegisterUiState> = _state.asStateFlow()

    // Canal de eventos de UI
    private val _events = Channel<UiEvent>(Channel.Factory.BUFFERED)
    val events = _events.receiveAsFlow()

    fun onNameChange(name: String) {
        _state.value = _state.value.copy(
            name = name,
            nameError = validateName(name),
            submitError = null
        )
    }

    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(
            email = email,
            emailError = validateEmail(email),
            submitError = null
        )
    }

    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(
            password = password,
            passwordError = validatePassword(password),
            repeatPasswordError = validateRepeatPassword(password, _state.value.repeatPassword),
            submitError = null
        )
    }

    fun onRepeatPasswordChange(repeatPassword: String) {
        _state.value = _state.value.copy(
            repeatPassword = repeatPassword,
            repeatPasswordError = validateRepeatPassword(_state.value.password, repeatPassword),
            submitError = null
        )
    }

    fun onRegisterClick() = viewModelScope.launch {
        val s = _state.value

        // Revalidar todo
        val nameErr = validateName(s.name)
        val emailErr = validateEmail(s.email)
        val passErr = validatePassword(s.password)
        val repeatErr = validateRepeatPassword(s.password, s.repeatPassword)

        if (nameErr != null || emailErr != null || passErr != null || repeatErr != null) {
            _state.value = s.copy(
                nameError = nameErr,
                emailError = emailErr,
                passwordError = passErr,
                repeatPasswordError = repeatErr
            )
            return@launch
        }

        // Activar loading
        _state.value = s.copy(isLoading = true, submitError = null)

        // Ejecutar caso de uso
        val result = registerUseCase(s.email, s.password, s.name)

        _state.value = _state.value.copy(isLoading = false)

        result.onSuccess {
            _events.send(UiEvent.ShowSnackbar("Cuenta creada correctamente. Inicia sesión"))
            _events.send(UiEvent.NavigateBack)
        }.onFailure { ex ->
            _state.value = _state.value.copy(
                submitError = ex.message ?: "Error al crear la cuenta"
            )
        }
    }

    fun onBackClick() = viewModelScope.launch {
        _events.send(UiEvent.NavigateBack)
    }

    // Validaciones
    private fun validateName(name: String): String? =
        when {
            name.isBlank() -> "El nombre es obligatorio"
            name.length < 2 -> "Mínimo 2 caracteres"
            else -> null
        }

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

    private fun validateRepeatPassword(password: String, repeatPassword: String): String? =
        when {
            repeatPassword.isBlank() -> "Repite la contraseña"
            password != repeatPassword -> "Las contraseñas no coinciden"
            else -> null
        }
}