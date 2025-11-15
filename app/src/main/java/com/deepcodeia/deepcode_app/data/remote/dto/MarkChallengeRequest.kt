package com.deepcodeia.deepcode_app.data.remote.dto

/**
 * DTO para marcar un reto como completado.
 * Request para POST /progress
 */
data class MarkChallengeRequest(
    val challengeId: Long,
    val notes: String? = null
)