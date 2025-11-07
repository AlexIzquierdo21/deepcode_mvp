package com.deepcodeia.deepcode_app.data.remote.dto

/**
 * DTO para la petición de registro.
 * Se envía al endpoint POST /auth/register
 */
data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)