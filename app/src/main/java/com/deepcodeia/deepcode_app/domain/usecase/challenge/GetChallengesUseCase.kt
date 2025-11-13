package com.deepcodeia.deepcode_app.domain.usecase.challenge

import com.deepcodeia.deepcode_app.domain.model.Challenge
import com.deepcodeia.deepcode_app.domain.repository.ChallengeRepository
import javax.inject.Inject

/**
 * Caso de uso: Obtener lista de retos con filtros opcionales.
 */
class GetChallengesUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository
) {
    /**
     * Obtiene la lista de retos desde el backend.
     * @param language Filtro por lenguaje (null = todos)
     * @param level Filtro por nivel (null = todos)
     * @return Result con lista de Challenge, o Exception si falla
     */
    suspend operator fun invoke(
        language: String? = null,
        level: String? = null
    ): Result<List<Challenge>> {
        return challengeRepository.getChallenges(language, level)
    }
}