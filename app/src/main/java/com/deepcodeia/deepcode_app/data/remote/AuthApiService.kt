package com.deepcodeia.deepcode_app.data.remote

import com.deepcodeia.deepcode_app.data.remote.dto.AuthResponse
import com.deepcodeia.deepcode_app.data.remote.dto.LoginRequest
import com.deepcodeia.deepcode_app.data.remote.dto.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Interfaz que define los endpoints de autenticación.
 */
interface AuthApiService {
    /**
     * Endpoint de login.
     * POST /auth/login
     */
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    /**
     * Endpoint de registro.
     * POST /auth/register
     */
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): AuthResponse
}