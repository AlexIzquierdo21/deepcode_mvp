package com.deepcodeia.deepcode_app.domain.usecase.user

import com.deepcodeia.deepcode_app.domain.model.UserProgress
import com.deepcodeia.deepcode_app.domain.repository.ProgressRepository
import javax.inject.Inject

/**
 * Caso de uso: Obtener el progreso de retos del usuario.
 * Calcula estadísticas de retos completados vs totales.
 */
class GetUserProgressUseCase @Inject constructor(
    private val progressRepository: ProgressRepository
) {
    /**
     * Obtiene el progreso del usuario desde el backend.
     * @return Result con lista de UserProgress, o Exception si falla
     */
    suspend operator fun invoke(): Result<List<UserProgress>> {
        return progressRepository.getUserProgress()
    }
}