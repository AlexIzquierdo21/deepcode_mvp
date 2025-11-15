package com.deepcodeia.deepcode_app.data.remote.apiservice

import com.deepcodeia.deepcode_app.data.remote.dto.MarkChallengeRequest
import com.deepcodeia.deepcode_app.data.remote.dto.UserChallengeDto
import com.deepcodeia.deepcode_app.data.remote.dto.UserProgressDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Interfaz que define los endpoints relacionados con el progreso del usuario.
 */
interface ProgressApiService {

    /**
     * Obtiene el progreso de retos del usuario autenticado.
     * Requiere token JWT en el header Authorization.
     * GET /progress/me
     */
    @GET("progress/me")
    suspend fun getUserProgress(): List<UserProgressDto>

    /**
     * Marca un reto como completado.
     * POST /progress
     */
    @Headers("Content-Type: application/json")
    @POST("progress")
    suspend fun markChallengeAsCompleted(
        @Body request: MarkChallengeRequest
    )
}