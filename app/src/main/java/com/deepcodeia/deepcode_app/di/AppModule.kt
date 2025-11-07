package com.deepcodeia.deepcode_app.di

import com.deepcodeia.deepcode_app.data.remote.AuthApiService
// import com.deepcodeia.deepcode_app.data.repository.FakeAuthRepository
import com.deepcodeia.deepcode_app.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import android.content.Context
import com.deepcodeia.deepcode_app.data.repository.AuthRepositoryImpl
import dagger.hilt.android.qualifiers.ApplicationContext


/**
 * Módulo de Hilt que provee las dependencias principales de la app.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * URL base de tu backend.
     * IMPORTANTE: Cambia esto según tu configuración:
     * - Emulador Android: http://10.0.2.2:8080/
     * - Dispositivo real en misma red: http://TU_IP_LOCAL:8080/
     * - Producción: https://tu-dominio.com/
     */
    private const val BASE_URL = "http://10.0.2.2:8080/"

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl // <- Usa el real (antes FakeAuthRepository)
    ): AuthRepository
}

@Module
@InstallIn(SingletonComponent::class)
object AppContextModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }
}