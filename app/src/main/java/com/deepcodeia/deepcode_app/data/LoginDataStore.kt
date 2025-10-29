package com.deepcodeia.deepcode_app.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences

// Clase para guardar y leer los datos de login (usuario y contraseña)
class LoginDataStore(private val context: Context) {

    companion object {
        // Se crea una instancia única de DataStore llamada "LoginDataStore"
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("LoginDataStore")
        // Claves para guardar los valores de usuario y contraseña
        private val USERNAME = stringPreferencesKey("userName")
        private val PASSWORD = stringPreferencesKey("password")
    }

    // Flujo que emite los datos guardados (usuario y contraseña)
    // Cada vez que cambian los datos en el DataStore, este Flow emite un nuevo LoginModel
    val loginModel: Flow<LoginModel> =
        context.dataStore.data.map { preferences ->
            LoginModel(
                preferences[USERNAME] ?: "", // Si no hay valor guardado, devuelve ""
                preferences[PASSWORD] ?: ""
            )
        }

    // Función para guardar los datos de login (usuario y contraseña) en el DataStore
    suspend fun saveLoginData(loginModel: LoginModel) {
        context.dataStore.edit { preferences ->
            preferences[USERNAME] = loginModel.userName
            preferences[PASSWORD] = loginModel.password
        }
    }
}

private fun DataStore<Preferences>.edit(transform: suspend (MutablePreferences) -> Unit) {
    TODO("No está implementado")
}

// Data class que representa el modelo de login
// Contiene solo el nombre de usuario y la contraseña
data class LoginModel(val userName: String, val password: String)
