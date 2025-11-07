package com.deepcodeia.deepcode_app.data.remote.dto

/**
 * DTO para la petición de login.
 * Se envía al endpoint POST /auth/login
 */
data class LoginRequest(
    val email: String,
    val password: String
)