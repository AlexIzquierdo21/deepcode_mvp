package com.deepcodeia.deepcode_app.di

import com.deepcodeia.deepcode_app.data.repository.FakeAuthRepository
import com.deepcodeia.deepcode_app.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Módulo de Hilt que provee las dependencias principales de la app.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    /**
     * Le dice a Hilt que cuando alguien pida AuthRepository,
     * debe usar FakeAuthRepository como implementación.
     */
    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: FakeAuthRepository
    ): AuthRepository
}