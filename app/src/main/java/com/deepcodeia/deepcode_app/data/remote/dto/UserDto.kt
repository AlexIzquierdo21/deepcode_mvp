package com.deepcodeia.deepcode_app.data.remote.dto

/**
 * DTO que representa la respuesta del endpoint GET /users/me
 */
data class UserDto(
    val id: Long,
    val username: String,
    val email: String,
    val createdAt: String
)