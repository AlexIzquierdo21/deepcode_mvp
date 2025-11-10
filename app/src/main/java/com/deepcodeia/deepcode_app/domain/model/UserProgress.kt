package com.deepcodeia.deepcode_app.domain.model

/**
 * Entidad del dominio que representa el progreso de un reto por el usuario.
 */
data class UserProgress(
    val challengeId: Long,
    val challengeTitle: String,
    val status: String,  // "PENDING" o "COMPLETED"
    val completedAt: String?
)