package com.deepcodeia.deepcode_app.di

import android.content.Context
import com.deepcodeia.deepcode_app.data.remote.AuthApiService
import com.deepcodeia.deepcode_app.data.repository.AuthRepositoryImpl
import com.deepcodeia.deepcode_app.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * NetworkModule - Configuración de Retrofit y OkHttp
 *
 * Provee las dependencias relacionadas con la red:
 * - HttpLoggingInterceptor: Para ver logs de peticiones HTTP en Logcat
 * - OkHttpClient: Cliente HTTP con timeouts y logging
 * - Retrofit: Cliente REST para comunicarse con el backend
 * - AuthApiService: Interfaz con los endpoints de autenticación
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * URL base del backend.
     * IMPORTANTE: Cambiar según donde se ejecute la app:
     * - Emulador Android: http://10.0.2.2:8080/ (ACTUAL)
     * - Dispositivo físico en misma red: http://TU_IP_LOCAL:8080/
     * - Producción: https://tu-dominio.com/
     */
    private const val BASE_URL = "http://10.0.2.2:8080/"

    /**
     * Provee un interceptor de logging para ver las peticiones HTTP en Logcat.
     * Nivel BODY: muestra URL, headers, body de request y response.
     */
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    /**
     * Provee el cliente HTTP (OkHttp) configurado con:
     * - Logging interceptor para debug
     * - Timeouts de 30 segundos para conexión, lectura y escritura
     */
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

    /**
     * Provee la instancia de Retrofit configurada con:
     * - URL base del backend
     * - Cliente OkHttp personalizado
     * - Conversor Gson para serialización JSON
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

    /**
     * Provee la interfaz de AuthApiService creada dinámicamente por Retrofit.
     * Esta interfaz define los endpoints de autenticación (login, register).
     */
    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }
}

/**
 * RepositoryModule - Inyección de repositorios
 * Conecta las interfaces del dominio con sus implementaciones concretas.
 * Usa @Binds para que Hilt sepa qué implementación usar.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Vincula AuthRepository (interfaz del dominio) con AuthRepositoryImpl (implementación real).
     * Cuando alguien pida AuthRepository, Hilt inyectará AuthRepositoryImpl.
     */
    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository
}

/**
 * AppContextModule - Provee el Context de la aplicación
 * Necesario para que DataStore y otros componentes reciban el Context.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppContextModule {

    /**
     * Provee el Context de la aplicación.
     * Usado por LoginDataStore y otros componentes que necesitan Context.
     */
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }
}