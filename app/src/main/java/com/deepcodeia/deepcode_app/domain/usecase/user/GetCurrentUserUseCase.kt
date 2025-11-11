package com.deepcodeia.deepcode_app.domain.usecase.user

import com.deepcodeia.deepcode_app.domain.model.User
import com.deepcodeia.deepcode_app.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Caso de uso: Obtener información del usuario actual.
 * Ejecuta la operación de obtener los datos del usuario autenticado.
 */
class GetCurrentUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    /**
     * Obtiene la información del usuario autenticado desde el backend.
     * @return Result con el User si es exitoso.
     */
    suspend operator fun invoke(): Result<User> {
        return userRepository.getCurrentUser()
    }
}