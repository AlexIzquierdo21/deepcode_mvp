package com.deepcodeia.deepcode_app.data.remote.apiservice

import com.deepcodeia.deepcode_app.data.remote.dto.UserDto
import retrofit2.http.GET

/**
 * Interfaz que define los endpoints relacionados con usuarios.
 */
interface UserApiService {

    /**
     * Obtiene la información del usuario autenticado.
     * Requiere token JWT en el header Authorization.
     * GET /user/me
     */
    @GET("user/me")
    suspend fun getCurrentUser(): UserDto
}