package com.deepcodeia.deepcode_app.config

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences



class LoginDataStore(private val context: Context) {

    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("LoginDataStore")
        private val USERNAME = stringPreferencesKey("userName")
        private val PASSWORD = stringPreferencesKey("password")
    }

    val loginModel : Flow<LoginModel> =
        context.dataStore.data.map { preferences ->
            LoginModel(
                preferences[USERNAME] ?: "",
                preferences[PASSWORD] ?: ""
            )
        }

    suspend fun saveLoginData(loginModel: LoginModel){
        context.dataStore.edit { preferences ->
            preferences[USERNAME] = loginModel.userName
            preferences[PASSWORD] = loginModel.password
        }
    }

}

private fun DataStore<Preferences>.edit(transform: suspend (MutablePreferences) -> Unit) {
    TODO("No está implementado")
}

// Data Class

data class LoginModel(val userName: String, val password: String)