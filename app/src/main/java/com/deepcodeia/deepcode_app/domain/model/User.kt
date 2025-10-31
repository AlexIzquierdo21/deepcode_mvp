package com.deepcodeia.deepcode_app.domain.model

/**
 * Entidad del dominio que representa un usuario.
 * Independiente de la implementación (API, BD, etc.)
 */
data class User(
    val id: String,
    val email: String,
    val name: String? = null
)