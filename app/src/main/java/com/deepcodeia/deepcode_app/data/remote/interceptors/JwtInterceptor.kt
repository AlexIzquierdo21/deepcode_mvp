package com.deepcodeia.deepcode_app.data.remote.interceptors

import com.deepcodeia.deepcode_app.data.LoginDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Interceptor que añade automáticamente el token JWT a todas las peticiones HTTP.
 * Lee el token desde DataStore y lo añade al header Authorization.
 */
class JwtInterceptor @Inject constructor(
    private val dataStore: LoginDataStore
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Obtener el token del DataStore (bloquea la coroutine)
        val token = runBlocking {
            dataStore.token.first()
        }

        // Si hay token, añadirlo al header
        val newRequest = if (!token.isNullOrBlank()) {
            originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            originalRequest
        }

        return chain.proceed(newRequest)
    }
}