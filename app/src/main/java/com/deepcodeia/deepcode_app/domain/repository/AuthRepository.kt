package com.deepcodeia.deepcode_app.domain.repository

import com.deepcodeia.deepcode_app.domain.model.User

/**
 * Contrato (interfaz) del repositorio de autenticación.
 * Define QUÉ operaciones se pueden hacer, no CÓMO.
 */
interface AuthRepository {
    /**
     * Intenta hacer login con email y password.
     * @return Result.success(User) si es correcto
     * @return Result.failure(Exception) si falla
     */
    suspend fun login(email: String, password: String): Result<User>
    /**
     * Cierra la sesión del usuario actual.
     */
    suspend fun logout(): Result<Unit>
    /**
     * Registra un nuevo usuario en el sistema.
     * @return Result.success(User) si el registro es correcto
     * @return Result.failure(Exception) si falla (ej: email ya existe)
     */
    suspend fun register(email: String, password: String, name: String): Result<User>

}