package com.deepcodeia.deepcode_app.data.repository

import com.deepcodeia.deepcode_app.data.remote.ChallengeApiService
import com.deepcodeia.deepcode_app.domain.model.Challenge
import com.deepcodeia.deepcode_app.domain.repository.ChallengeRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementación del repositorio de retos.
 * Usa Retrofit para comunicarse con el backend.
 */
@Singleton
class ChallengeRepositoryImpl @Inject constructor(
    private val challengeApiService: ChallengeApiService
) : ChallengeRepository {

    override suspend fun getChallenges(
        language: String?,
        level: String?
    ): Result<List<Challenge>> {
        return try {
            val challengeDtos = challengeApiService.getChallenges(language, level)

            // Convertir DTOs a entidades del dominio
            val challenges = challengeDtos.map { dto ->
                Challenge(
                    id = dto.id,
                    title = dto.title,
                    description = dto.description,
                    programmingLanguage = dto.programmingLanguage,
                    level = dto.level,
                    createdBy = dto.createdBy.username,
                    createdAt = dto.createdAt
                )
            }

            Result.success(challenges)
        } catch (e: Exception) {
            Result.failure(Exception("Error al obtener retos: ${e.message}"))
        }
    }
}