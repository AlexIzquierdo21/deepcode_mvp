package com.deepcodeia.deepcode_app.data.remote.dto

/**
 * DTO que representa un reto completado por el usuario.
 * Respuesta del endpoint GET /progress/me
 */
data class UserProgressDto(
    val id: Long,
    val challengeId: Long,
    val challengeTitle: String,
    val status: String,  // "PENDING" o "COMPLETED"
    val notes: String?,
    val completedAt: String?
)