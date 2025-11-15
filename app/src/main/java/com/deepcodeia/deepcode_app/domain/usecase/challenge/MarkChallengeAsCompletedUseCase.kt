package com.deepcodeia.deepcode_app.domain.usecase.challenge

import com.deepcodeia.deepcode_app.domain.repository.ProgressRepository
import javax.inject.Inject

/**
 * Caso de uso: Marcar un reto como completado.
 */
class MarkChallengeAsCompletedUseCase @Inject constructor(
    private val progressRepository: ProgressRepository
) {
    /**
     * Marca un reto como completado para el usuario autenticado.
     * @param challengeId ID del reto a marcar
     * @return Result vacío si es exitoso, o Exception si falla
     */
    suspend operator fun invoke(challengeId: Long): Result<Unit> {
        return progressRepository.markChallengeAsCompleted(challengeId)
    }
}