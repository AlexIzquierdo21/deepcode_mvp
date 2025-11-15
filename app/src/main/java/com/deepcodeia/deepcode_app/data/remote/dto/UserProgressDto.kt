package com.deepcodeia.deepcode_app.data.remote.dto


/**
 * DTO que representa el progreso de un reto del usuario.
 * Respuesta del endpoint GET /progress/me
 */
data class UserProgressDto(
    val id: Long,
    val challengeId: ChallengeInfoDto,  // ← Objeto, no Long
    val status: String,
    val completedAt: String?
)

/**
 * Info básica del reto dentro de UserProgress.
 * Reutilizamos ChallengeInfoDto que ya existe.
 */
data class ChallengeInfoDto(
    val id: Long,
    val title: String
)