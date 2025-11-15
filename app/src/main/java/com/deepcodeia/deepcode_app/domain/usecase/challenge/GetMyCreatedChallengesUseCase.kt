package com.deepcodeia.deepcode_app.domain.usecase.challenge

import com.deepcodeia.deepcode_app.domain.model.Challenge
import com.deepcodeia.deepcode_app.domain.repository.ChallengeRepository
import javax.inject.Inject

/**
 * Caso de uso: Obtener los retos creados por el usuario autenticado.
 */
class GetMyCreatedChallengesUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository
) {
    /**
     * Obtiene los retos creados por el usuario desde el backend.
     * @return Result con lista de Challenge, o Exception si falla
     */
    suspend operator fun invoke(): Result<List<Challenge>> {
        return challengeRepository.getMyCreatedChallenges()
    }
}