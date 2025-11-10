package com.deepcodeia.deepcode_app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * AppModule - Dependencias generales de la aplicación
 *
 * Este módulo provee dependencias globales que no encajan en módulos específicos.
 * Actualmente solo provee el Context de la aplicación.
 *
 * Organización de módulos DI:
 * - AppModule: Dependencias generales (Context)
 * - NetworkModule: Configuración de red (Retrofit, OkHttp)
 * - ApiModule: ApiServices (AuthApiService, UserApiService, etc.)
 * - RepositoryModule: Repositorios (vincula interfaces con implementaciones)
 *
 * IMPORTANTE: Este archivo SOLO debe contener el módulo AppModule.
 * Los demás módulos (NetworkModule, ApiModule, RepositoryModule) están en archivos separados.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provee el Context de la aplicación.
     * Usado por: LoginDataStore, JwtInterceptor y otros componentes que necesitan Context.
     *
     * @ApplicationContext asegura que es el Context de la Application,
     * no de una Activity (para evitar memory leaks).
     */
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }
}