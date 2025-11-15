package com.deepcodeia.deepcode_app.domain.usecase.challenge

import com.deepcodeia.deepcode_app.domain.repository.ChallengeRepository
import javax.inject.Inject

/**
 * Caso de uso: Eliminar un reto creado por el usuario.
 */
class DeleteChallengeUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository
) {
    /**
     * Elimina un reto por su ID.
     * Solo el creador puede eliminar (validado en backend).
     * @param challengeId ID del reto a eliminar
     * @return Result vacío si es exitoso, o Exception si falla
     */
    suspend operator fun invoke(challengeId: Long): Result<Unit> {
        return challengeRepository.deleteChallenge(challengeId)
    }
}