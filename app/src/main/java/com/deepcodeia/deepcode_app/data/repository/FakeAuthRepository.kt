package com.deepcodeia.deepcode_app.data.repository

import com.deepcodeia.deepcode_app.domain.model.User
import com.deepcodeia.deepcode_app.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementación FAKE del repositorio de autenticación.
 * Simula una API real con delays y validaciones básicas.
 *
 * En el futuro se reemplazará por una implementación real con Retrofit.
 */
@Singleton
class FakeAuthRepository @Inject constructor() : AuthRepository {

    // Usuario fake para testing
    private val fakeUser = User(
        id = "1",
        email = "test@test.com",
        name = "Usuario Test"
    )

    override suspend fun login(email: String, password: String): Result<User> {
        // Simula latencia de red
        delay(1000)

        // Validación fake: cualquier email con password "123456" funciona
        return if (password == "123456") {
            Result.success(fakeUser.copy(email = email))
        } else {
            Result.failure(Exception("Credenciales incorrectas"))
        }
    }

    override suspend fun logout(): Result<Unit> {
        delay(500)
        return Result.success(Unit)
    }
    override suspend fun register(
        email: String,
        password: String,
        name: String
    ): Result<User> {
        // Simula latencia de red
        delay(1000)

        // Simula que el registro siempre funciona
        // En una implementación real, aquí verificarías si el email ya existe
        return Result.success(
            User(
                id = System.currentTimeMillis().toString(), // ID único fake
                email = email,
                name = name
            )
        )
    }
}