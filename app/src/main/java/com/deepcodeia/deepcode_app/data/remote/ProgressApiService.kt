package com.deepcodeia.deepcode_app.data.remote

import com.deepcodeia.deepcode_app.data.remote.dto.UserProgressDto
import retrofit2.http.GET

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
}