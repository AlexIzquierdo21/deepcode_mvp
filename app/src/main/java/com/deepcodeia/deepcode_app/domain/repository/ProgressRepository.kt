package com.deepcodeia.deepcode_app.domain.repository

import com.deepcodeia.deepcode_app.domain.model.UserProgress

/**
 * Interfaz del repositorio de progreso.
 * Define las operaciones relacionadas con el progreso de retos del usuario.
 */
interface ProgressRepository {

    /**
     * Obtiene el progreso de todos los retos del usuario autenticado.
     * @return Result con lista de UserProgress si es exitoso, o Exception si falla
     */
    suspend fun getUserProgress(): Result<List<UserProgress>>

    /**
     * Marca un reto como completado.
     * @param challengeId ID del reto a marcar como completado
     * @return Result vacío si es exitoso, o Exception si falla
     */
    suspend fun markChallengeAsCompleted(challengeId: Long): Result<Unit>
}