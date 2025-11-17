package com.deepcodeia.deepcode_app.data.repository

import com.deepcodeia.deepcode_app.data.remote.apiservice.ProgressApiService
import com.deepcodeia.deepcode_app.data.remote.dto.MarkChallengeRequest
import com.deepcodeia.deepcode_app.domain.model.UserProgress
import com.deepcodeia.deepcode_app.domain.repository.ProgressRepository
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.HttpException

/**
 * Implementación del repositorio de progreso.
 * Usa Retrofit para comunicarse con el backend, mediante  ApiModule.kt.
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

    override suspend fun markChallengeAsCompleted(challengeId: Long): Result<Unit> {
        return try {
            val request = MarkChallengeRequest(challengeId = challengeId)
            progressApiService.markChallengeAsCompleted(request)
            Result.success(Unit)
        } catch (e: retrofit2.HttpException) {
            // LOG para ver el código exacto
            println("HTTP Error Code: ${e.code()}")
            println("HTTP Error Message: ${e.message()}")

            // Manejar errores HTTP específicos
            val errorMessage = when (e.code()) {
                400 -> "Ya completaste este reto"
                401 -> "Sesión expirada. Por favor inicia sesión de nuevo"
                403 -> "No tienes permisos para completar este reto"
                404 -> "Reto no encontrado"
                500 -> "Error interno del servidor: ${e.message()}"
                else -> "Error al marcar reto (código ${e.code()}): ${e.message()}"
            }
            Result.failure(Exception(errorMessage))
        } catch (e: Exception) {
            println("Exception: ${e.message}")
            Result.failure(Exception("Error de conexión: ${e.message}"))
        }
    }
}