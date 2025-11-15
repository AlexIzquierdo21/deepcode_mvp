package com.deepcodeia.deepcode_app.data.remote.dto

/**
 * DTO para crear un nuevo reto.
 * Request para POST /challenges
 */
data class CreateChallengeRequest(
    val title: String,
    val description: String,
    val language: String,      // "PYTHON", "JAVA", "KOTLIN", "HTML_CSS_JS"
    val level: String          // "BEGINNER", "INTERMEDIATE"
)