package com.deepcodeia.deepcode_app.data.repository

import com.deepcodeia.deepcode_app.data.LoginDataStore
import com.deepcodeia.deepcode_app.data.LoginModel
import com.deepcodeia.deepcode_app.data.remote.AuthApiService
import com.deepcodeia.deepcode_app.data.remote.dto.LoginRequest
import com.deepcodeia.deepcode_app.data.remote.dto.RegisterRequest
import com.deepcodeia.deepcode_app.domain.model.User
import com.deepcodeia.deepcode_app.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementación real del repositorio de autenticación.
 * Usa Retrofit para comunicarse con el backend.
 */
@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService,
    private val dataStore: LoginDataStore
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            // Llama al backend con Retrofit
            val response = authApiService.login(
                LoginRequest(email = email, password = password)
            )

            // Guardar token y email en DataStore
            dataStore.saveLoginData(
                LoginModel(
                    userName = response.email,
                    password = "" // No guardamos password
                )
            )
            dataStore.saveToken(response.token)

            // Convertir respuesta a modelo del dominio
            Result.success(
                User(
                    id = 0, // El backend no devuelve el ID en login
                    email = response.email,
                    name = response.username
                )
            )
        } catch (e: Exception) {
            Result.failure(Exception("Error al iniciar sesión: ${e.message}"))
        }
    }

    override suspend fun register(
        email: String,
        password: String,
        name: String
    ): Result<User> {
        return try {
            val response = authApiService.register(
                RegisterRequest(
                    username = name,
                    email = email,
                    password = password
                )
            )

            // Convertir respuesta a modelo del dominio
            Result.success(
                User(
                    id = 0,
                    email = response.email,
                    name = response.username
                )
            )
        } catch (e: Exception) {
            Result.failure(Exception("Error al registrar: ${e.message}"))
        }
    }

    override suspend fun logout(): Result<Unit> {
        return try {
            dataStore.clearData()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}