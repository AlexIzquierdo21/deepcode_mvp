package com.deepcodeia.deepcode_app.domain.repository

import com.deepcodeia.deepcode_app.domain.model.Challenge

/**
 * Interfaz del repositorio de retos.
 * Define las operaciones relacionadas con retos de programación.
 */
interface ChallengeRepository {

    /**
     * Obtiene la lista de retos con filtros opcionales.
     * @param language Filtro por lenguaje (null = todos)
     * @param level Filtro por nivel (null = todos)
     * @return Result con lista de Challenge si es exitoso, o Exception si falla
     */
    suspend fun getChallenges(
        language: String? = null,
        level: String? = null
    ): Result<List<Challenge>>
}