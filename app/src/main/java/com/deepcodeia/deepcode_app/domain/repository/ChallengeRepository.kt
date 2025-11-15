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

    /**
     * Crea un nuevo reto.
     * @param title Título del reto
     * @param description Descripción del reto
     * @param language Lenguaje de programación
     * @param level Nivel de dificultad
     * @return Result con el Challenge creado si es exitoso, o Exception si falla
     */
    suspend fun createChallenge(
        title: String,
        description: String,
        language: String,
        level: String
    ): Result<Challenge>
}