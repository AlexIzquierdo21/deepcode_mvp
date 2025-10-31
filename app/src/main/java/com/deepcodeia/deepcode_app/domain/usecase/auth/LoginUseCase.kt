package com.deepcodeia.deepcode_app.domain.usecase.auth

import com.deepcodeia.deepcode_app.domain.model.User
import com.deepcodeia.deepcode_app.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * Caso de uso: Login de usuario.
 * Contiene la lógica de negocio para el inicio de sesión.
 */
class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    /**
     * Ejecuta el login.
     * @return Result con el User si es exitoso, o Exception si falla
     */
    suspend operator fun invoke(email: String, password: String): Result<User> {
        // Aquí podrías añadir validaciones adicionales de negocio
        // Por ejemplo: verificar que el email tenga formato correcto

        return authRepository.login(email, password)
    }
}