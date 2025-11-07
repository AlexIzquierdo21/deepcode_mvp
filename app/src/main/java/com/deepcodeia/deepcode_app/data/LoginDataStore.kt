package com.deepcodeia.deepcode_app.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

// Extension property para crear el DataStore
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("LoginDataStore")

/**
 * Clase para guardar y leer los datos de login y el token JWT.
 */
@Singleton
class LoginDataStore @Inject constructor(
    private val context: Context
) {
    companion object {
        private val USERNAME = stringPreferencesKey("userName")
        private val PASSWORD = stringPreferencesKey("password")
        private val TOKEN = stringPreferencesKey("token")
    }

    /**
     * Flow que emite los datos de login guardados.
     */
    val loginModel: Flow<LoginModel> =
        context.dataStore.data.map { preferences ->
            LoginModel(
                preferences[USERNAME] ?: "",
                preferences[PASSWORD] ?: ""
            )
        }

    /**
     * Flow que emite el token JWT guardado.
     */
    val token: Flow<String?> =
        context.dataStore.data.map { preferences ->
            preferences[TOKEN]
        }

    /**
     * Guarda los datos de login.
     */
    suspend fun saveLoginData(loginModel: LoginModel) {
        context.dataStore.edit { preferences ->
            preferences[USERNAME] = loginModel.userName
            preferences[PASSWORD] = loginModel.password
        }
    }

    /**
     * Guarda el token JWT.
     */
    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN] = token
        }
    }

    /**
     * Limpia todos los datos guardados (logout).
     */
    suspend fun clearData() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}

/**
 * Data class que representa el modelo de login.
 */
data class LoginModel(
    val userName: String,
    val password: String
)
