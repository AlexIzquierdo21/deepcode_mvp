package com.deepcodeia.deepcode_app.data.repository

import com.deepcodeia.deepcode_app.data.remote.ProgressApiService
import com.deepcodeia.deepcode_app.domain.model.UserProgress
import com.deepcodeia.deepcode_app.domain.repository.ProgressRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementación del repositorio de progreso.
 * Usa Retrofit para comunicarse con el backend.
 */
@Singleton
class ProgressRepositoryImpl @Inject constructor(
    private val progressApiService: ProgressApiService
) : ProgressRepository {

    override suspend fun getUserProgress(): Result<List<UserProgress>> {
        return try {
            val progressDtos = progressApiService.getUserProgress()

            // Convertir DTOs a entidades del dominio
            val progressList = progressDtos.map { dto ->
                UserProgress(
                    challengeId = dto.challengeId,
                    challengeTitle = dto.challengeTitle,
                    status = dto.status,
                    completedAt = dto.completedAt
                )
            }

            Result.success(progressList)
        } catch (e: Exception) {
            Result.failure(Exception("Error al obtener progreso: ${e.message}"))
        }
    }
}