package com.deepcodeia.deepcode_app.data.remote.dto

/**
 * DTO para la respuesta de login/register.
 * Contiene el token JWT que devuelve el backend.
 */
data class AuthResponse(
    val token: String,
    val username: String,
    val email: String
)