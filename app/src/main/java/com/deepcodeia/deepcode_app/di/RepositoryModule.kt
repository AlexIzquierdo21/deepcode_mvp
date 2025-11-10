package com.deepcodeia.deepcode_app.di

import com.deepcodeia.deepcode_app.data.repository.AuthRepositoryImpl
import com.deepcodeia.deepcode_app.data.repository.ProgressRepositoryImpl
import com.deepcodeia.deepcode_app.data.repository.UserRepositoryImpl
import com.deepcodeia.deepcode_app.domain.repository.AuthRepository
import com.deepcodeia.deepcode_app.domain.repository.ProgressRepository
import com.deepcodeia.deepcode_app.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * RepositoryModule - Vincula interfaces con implementaciones
 *
 * Este módulo conecta las interfaces del dominio (contratos) con sus
 * implementaciones concretas en la capa de datos.
 *
 * Usa @Binds en lugar de @Provides para mejor rendimiento.
 *
 * Patrón:
 * - domain/repository/XRepository (interfaz - QUÉ)
 * - data/repository/XRepositoryImpl (implementación - CÓMO)
 *
 * Ejemplo: FakeAuthRepository → AuthRepositoryImpl sin cambiar UseCases.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Vincula AuthRepository con AuthRepositoryImpl.
     * Cuando alguien pida AuthRepository, Hilt inyectará AuthRepositoryImpl.
     */
    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    /**
     * Vincula UserRepository con UserRepositoryImpl.
     * Cuando alguien pida UserRepository, Hilt inyectará UserRepositoryImpl.
     */
    @Binds
    @Singleton
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository

    /**
     * Vincula ProgressRepository con ProgressRepositoryImpl.
     * Cuando alguien pida ProgressRepository, Hilt inyectará ProgressRepositoryImpl.
     */
    @Binds
    @Singleton
    abstract fun bindProgressRepository(
        impl: ProgressRepositoryImpl
    ): ProgressRepository
}