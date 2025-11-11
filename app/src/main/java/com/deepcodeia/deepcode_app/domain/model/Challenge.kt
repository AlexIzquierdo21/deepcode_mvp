package com.deepcodeia.deepcode_app.domain.model

/**
 * Entidad del dominio que representa un reto de programación.
 */
data class Challenge(
    val id: Long,
    val title: String,
    val description: String,
    val programmingLanguage: String,  // "PYTHON", "JAVA", "KOTLIN", "HTML_CSS_JS"
    val level: String,                // "BEGINNER", "INTERMEDIATE"
    val createdBy: String,
    val createdAt: String
)