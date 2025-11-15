package com.deepcodeia.deepcode_app.domain.usecase.challenge

import com.deepcodeia.deepcode_app.domain.model.Challenge
import com.deepcodeia.deepcode_app.domain.repository.ChallengeRepository
import javax.inject.Inject

/**
 * Caso de uso: Crear un nuevo reto de programación.
 */
class CreateChallengeUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository
) {
    /**
     * Crea un nuevo reto en el backend.
     * @return Result con el Challenge creado, o Exception si falla
     */
    suspend operator fun invoke(
        title: String,
        description: String,
        language: String,
        level: String
    ): Result<Challenge> {
        return challengeRepository.createChallenge(title, description, language, level)
    }
}