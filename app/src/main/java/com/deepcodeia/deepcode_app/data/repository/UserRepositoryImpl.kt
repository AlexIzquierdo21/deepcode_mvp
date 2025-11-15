package com.deepcodeia.deepcode_app.data.repository

import android.util.Log
import com.deepcodeia.deepcode_app.data.remote.apiservice.UserApiService
import com.deepcodeia.deepcode_app.domain.model.User
import com.deepcodeia.deepcode_app.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementación del repositorio de usuarios.
 * Usa Retrofit para comunicarse con el backend.
 */
@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userApiService: UserApiService
) : UserRepository {

    override suspend fun getCurrentUser(): Result<User> {
        //val userDto = userApiService.getCurrentUser()
        return try {
            val userDto = userApiService.getCurrentUser()

            // Convertir DTO a entidad del dominio
            Result.success(
                User(
                    id = userDto.id,
                    email = userDto.email,
                    name = userDto.username
                )
            )
        } catch (e: Exception) {
            Result.failure(Exception("Error al obtener usuario: ${e.message}"))
        }
    }
}