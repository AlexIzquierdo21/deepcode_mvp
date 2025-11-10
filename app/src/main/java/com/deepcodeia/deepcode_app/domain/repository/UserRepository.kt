package com.deepcodeia.deepcode_app.domain.repository

import com.deepcodeia.deepcode_app.domain.model.User

/**
 * Interfaz del repositorio de usuarios.
 * Define las operaciones relacionadas con la información del usuario.
 */
interface UserRepository {

    /**
     * Obtiene la información del usuario autenticado actual.
     * @return Result con el User si es exitoso, o Exception si falla
     */
    suspend fun getCurrentUser(): Result<User>
}