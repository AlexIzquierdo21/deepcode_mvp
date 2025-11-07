package com.deepcodeia.deepcode_app.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepcodeia.deepcode_app.data.LoginDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * SplashViewModel
 *
 * ViewModel responsable de verificar si existe un token JWT guardado
 * en DataStore al iniciar la aplicación.
 *
 * Funcionalidad:
 * - Lee el token desde DataStore
 * - Emite true si hay token (sesión activa)
 * - Emite false si no hay token (ir a login)
 * - Emite null mientras está verificando
 *
 * Esto permite implementar auto-login: si hay token válido guardado,
 * el usuario va directo a Home sin pasar por Login.
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStore: LoginDataStore
) : ViewModel() {

    /**
     * Estado mutable interno (privado).
     * null = verificando, true = hay token, false = no hay token
     */
    private val _hasToken = MutableStateFlow<Boolean?>(null)

    /**
     * Estado público inmutable que la UI observa.
     * La UI reacciona a los cambios para decidir la navegación inicial.
     */
    val hasToken: StateFlow<Boolean?> = _hasToken.asStateFlow()

    /**
     * Al crear el ViewModel, automáticamente verifica si hay token.
     */
    init {
        checkToken()
    }

    /**
     * Verifica si existe un token JWT guardado en DataStore.
     * - Lee el token desde el Flow de DataStore
     * - Usa .first() para obtener el valor actual (suspende hasta que esté disponible)
     * - Actualiza el estado según si el token existe y no está vacío
     */
    private fun checkToken() = viewModelScope.launch {
        val token = dataStore.token.first()
        _hasToken.value = !token.isNullOrBlank()
    }
}