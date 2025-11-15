package com.deepcodeia.deepcode_app.di

import com.deepcodeia.deepcode_app.data.remote.apiservice.AuthApiService
import com.deepcodeia.deepcode_app.data.remote.apiservice.ChallengeApiService
import com.deepcodeia.deepcode_app.data.remote.apiservice.ProgressApiService
import com.deepcodeia.deepcode_app.data.remote.apiservice.UserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * ApiModule - Provee todos los ApiServices
 *
 * Este módulo es responsable de crear las interfaces de API usando Retrofit.
 * Cada ApiService define los endpoints de una funcionalidad específica:
 * - AuthApiService: Autenticación (login, register)
 * - UserApiService: Información del usuario (/users/me)
 * - ProgressApiService: Progreso de retos (/progress/me)
 *
 * Separado de NetworkModule para mantener la organización:
 * - NetworkModule = Configuración base (Retrofit, OkHttp)
 * - ApiModule = Servicios específicos de la API
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    /**
     * Provee AuthApiService para endpoints de autenticación.
     * Endpoints públicos: /auth/login, /auth/register
     */
    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

    /**
     * Provee UserApiService para endpoints de usuarios.
     * Endpoints protegidos (requieren JWT): /users/me
     */
    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }

    /**
     * Provee ProgressApiService para endpoints de progreso.
     * Endpoints protegidos (requieren JWT): /progress/me
     */
    @Provides
    @Singleton
    fun provideProgressApiService(retrofit: Retrofit): ProgressApiService {
        return retrofit.create(ProgressApiService::class.java)
    }

    /**
     * Provee ChallengeApiService para endpoints de retos.
     * Endpoints protegidos (requieren JWT): /challenges
     */
    @Provides
    @Singleton
    fun provideChallengeApiService(retrofit: Retrofit): ChallengeApiService {
        return retrofit.create(ChallengeApiService::class.java)
    }
}