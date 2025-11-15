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
     * Obtiene los retos creados por el usuario autenticado.
     * @return Result con lista de Challenge creados por el usuario, o Exception si falla
     */
    suspend fun getMyCreatedChallenges(): Result<List<Challenge>>

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

    /**
     * Elimina un reto por su ID.
     * Solo el creador puede eliminar.
     * @param challengeId ID del reto a eliminar
     * @return Result vacío si es exitoso, o Exception si falla
     */
    suspend fun deleteChallenge(challengeId: Long): Result<Unit>
}