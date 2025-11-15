package com.deepcodeia.deepcode_app.data.repository

import com.deepcodeia.deepcode_app.data.remote.apiservice.ChallengeApiService
import com.deepcodeia.deepcode_app.data.remote.dto.CreateChallengeRequest
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

    override suspend fun createChallenge(
        title: String,
        description: String,
        language: String,
        level: String
    ): Result<Challenge> {
        return try {
            val request = CreateChallengeRequest(
                title = title,
                description = description,
                language = language,
                level = level
            )

            val challengeDto = challengeApiService.createChallenge(request)

            // Convertir DTO a entidad del dominio
            val challenge = Challenge(
                id = challengeDto.id,
                title = challengeDto.title,
                description = challengeDto.description,
                programmingLanguage = challengeDto.programmingLanguage,
                level = challengeDto.level,
                createdBy = challengeDto.createdBy.username,
                createdAt = challengeDto.createdAt
            )

            Result.success(challenge)
        } catch (e: Exception) {
            Result.failure(Exception("Error al crear reto: ${e.message}"))
        }
    }

    override suspend fun getMyCreatedChallenges(): Result<List<Challenge>> {
        return try {
            val challengeDtos = challengeApiService.getMyCreatedChallenges()

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
            Result.failure(Exception("Error al obtener tus retos: ${e.message}"))
        }
    }

    override suspend fun deleteChallenge(challengeId: Long): Result<Unit> {
        return try {
            val response = challengeApiService.deleteChallenge(challengeId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error al eliminar reto: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error al eliminar reto: ${e.message}"))
        }
    }
}