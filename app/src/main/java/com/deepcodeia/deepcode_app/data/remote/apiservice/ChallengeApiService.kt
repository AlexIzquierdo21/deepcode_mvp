package com.deepcodeia.deepcode_app.data.remote.apiservice

import com.deepcodeia.deepcode_app.data.remote.dto.ChallengeDto
import com.deepcodeia.deepcode_app.data.remote.dto.CreateChallengeRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interfaz que define los endpoints relacionados con retos.
 */
interface ChallengeApiService {
    /**
     * Obtiene la lista de retos.
     * Soporta filtros opcionales por lenguaje y nivel.
     * GET /challenges?language=PYTHON&level=BEGINNER
     */
    @GET("challenges")
    suspend fun getChallenges(
        @Query("language") language: String? = null,
        @Query("level") level: String? = null
    ): List<ChallengeDto>

    /**
     * Obtiene los retos creados por el usuario autenticado.
     * GET /challenges/my-challenges
     */
    @GET("challenges/my-challenges")
    suspend fun getMyCreatedChallenges(): List<ChallengeDto>

    /**
     * Crea un nuevo reto.
     * POST /challenges
     */
    @POST("challenges")
    suspend fun createChallenge(
        @Body request: CreateChallengeRequest
    ): ChallengeDto

    /**
     * Elimina un reto por su ID.
     * DELETE /challenges/{id}
     * Solo el creador puede eliminar.
     */
    @DELETE("challenges/{id}")
    suspend fun deleteChallenge(
        @Path("id") challengeId: Long
    ): Response<Unit>
}