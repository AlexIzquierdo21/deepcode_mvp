package com.deepcodeia.deepcode_app.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 *
 * ChallengeDto - DTO que representa un reto del backend
 *
 * Este DTO refleja EXACTAMENTE la estructura JSON que devuelve el backend
 * en el endpoint GET /challenges.
 *
 * IMPORTANTE: La estructura del DTO debe coincidir con el JSON del backend.
 * Si no coincide, Gson no podrá parsear correctamente y la app fallará.
 */
data class ChallengeDto(
    val id: Long,
    val title: String,
    val description: String,

    /**
     * PROBLEMA RESUELTO #1: Nombres de campos diferentes
     *
     * Backend envía:     "language": "PYTHON"
     * Frontend necesita: programmingLanguage (nombre más descriptivo)
     *
     * Solución: @SerializedName le dice a Gson:
     * "Busca el campo 'language' en el JSON del backend,
     *  pero en Kotlin úsalo como 'programmingLanguage'"
     *
     * Sin esta anotación, Gson buscaría "programmingLanguage" en el JSON,
     * no lo encontraría, y el valor sería null → ERROR.
     */
    @SerializedName("language")
    val programmingLanguage: String,

    val level: String,

    /**
     * PROBLEMA RESUELTO #2: Objeto anidado vs String
     *
     * Backend envía un OBJETO completo de usuario:
     * "createdBy": {
     *   "id": 1,
     *   "username": "test",
     *   "email": "test@email.com",
     *   "createdAt": "2025-11-04T13:27:05"
     * }
     *
     * Frontend inicialmente esperaba solo un String:
     * val createdBy: String INCORRECTO
     *
     * Error: "Expected a string but was BEGIN_OBJECT"
     * Razón: Gson intentó convertir un objeto JSON a String y falló.
     *
     * Solución: Crear un DTO anidado (UserSummaryDto) que refleje
     * la estructura real del objeto createdBy del backend.
     *
     * Luego, en ChallengeRepositoryImpl, extraemos solo el username:
     * createdBy = dto.createdBy.username
     */
    val createdBy: UserSummaryDto,

    val createdAt: String
)

/**
 *
 * UserSummaryDto - Información resumida de usuario
 *
 * Representa el objeto "createdBy" que viene anidado en el JSON de Challenge.
 *
 * Estructura JSON que parsea:
 * {
 *   "id": 1,
 *   "username": "test",
 *   "email": "test@email.com"
 * }
 *
 * Este DTO es necesario porque el backend devuelve un objeto completo
 * de usuario, no solo el nombre como String.
 */
data class UserSummaryDto(
    val id: Long,
    val username: String,
    val email: String
)