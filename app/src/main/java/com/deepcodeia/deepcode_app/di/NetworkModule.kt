package com.deepcodeia.deepcode_app.di

import com.deepcodeia.deepcode_app.data.remote.interceptors.JwtInterceptor
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

/**
 * NetworkModule - Configuración base de red
 *
 * Provee las dependencias fundamentales para la comunicación HTTP:
 * - HttpLoggingInterceptor: Para debug de peticiones
 * - OkHttpClient: Cliente HTTP con timeouts
 * - Retrofit: Cliente REST configurado
 *
 * Este módulo solo contiene la CONFIGURACIÓN BASE.
 * Los ApiServices específicos están en ApiModule.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * URL base del backend.
     * IMPORTANTE: Cambiar según donde se ejecute la app:
     * - Emulador Android: http://10.0.2.2:8080/ (ACTUAL)
     * - Dispositivo físico: http://TU_IP_LOCAL:8080/
     * - Producción: https://tu-dominio.com/
     */
    private const val BASE_URL = "http://10.42.54.172:8080/"

    /**
     * Provee un interceptor de logging para ver peticiones HTTP en Logcat.
     * Nivel BODY: muestra URL, headers, body de request/response.
     */
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    /**
     * Provee el cliente HTTP (OkHttp) con:
     * - Logging interceptor para debug
     * - Timeouts de 30 segundos
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        jwtInterceptor: JwtInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(jwtInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Provee la instancia principal de Retrofit.
     * Esta es la base que usarán todos los ApiServices.
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}