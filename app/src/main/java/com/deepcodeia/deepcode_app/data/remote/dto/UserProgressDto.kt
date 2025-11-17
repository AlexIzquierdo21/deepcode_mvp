package com.deepcodeia.deepcode_app.data.remote.dto

/**
 * DTO que representa el progreso de un reto del usuario.
 * Respuesta del endpoint GET /progress/me
 */
data class UserProgressDto(
    val challengeId: Long,           // ← Long directo, no objeto
    val challengeTitle: String,      // ← Añadir
    val language: String,            // ← Añadir
    val level: String,               // ← Añadir
    val status: String,
    val notes: String?,              // ← Añadir
    val completedAt: String?
)

