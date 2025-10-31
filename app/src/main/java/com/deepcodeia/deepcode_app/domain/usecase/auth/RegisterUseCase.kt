package com.deepcodeia.deepcode_app.domain.usecase.auth

import com.deepcodeia.deepcode_app.domain.model.User
import com.deepcodeia.deepcode_app.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * Caso de uso: Registro de nuevo usuario.
 */
class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    /**
     * Ejecuta el registro de un nuevo usuario.
     * @return Result con el User si es exitoso, o Exception si falla
     */
    suspend operator fun invoke(
        email: String,
        password: String,
        name: String
    ): Result<User> {
        // Aquí podrías añadir validaciones de negocio adicionales
        return authRepository.register(email, password, name)
    }
}