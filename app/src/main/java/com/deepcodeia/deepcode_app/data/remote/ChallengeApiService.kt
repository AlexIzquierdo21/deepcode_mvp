package com.deepcodeia.deepcode_app.data.remote

import com.deepcodeia.deepcode_app.data.remote.dto.ChallengeDto
import com.deepcodeia.deepcode_app.data.remote.dto.CreateChallengeRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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
     * Crea un nuevo reto.
     * POST /challenges
     */
    @POST("challenges")
    suspend fun createChallenge(
        @Body request: CreateChallengeRequest
    ): ChallengeDto
}