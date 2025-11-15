package com.deepcodeia.deepcode_app.data.remote.dto

/**
 * DTO que representa el progreso de un reto.
 * Respuesta del endpoint POST /progress
 */
data class UserChallengeDto(
    val id: Long,
    val challengeId: Long,
    val challengeTitle: String,
    val status: String,  // "PENDING", "COMPLETED"
    val completedAt: String?
)