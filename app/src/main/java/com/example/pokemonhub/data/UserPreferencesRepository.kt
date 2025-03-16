package com.example.pokemonhub.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val NOMBRE_USUARIO = stringPreferencesKey("nombre_usuario")
        val MODO_VISUALIZACION = stringPreferencesKey("modo_visualizacion")
        const val TAG = "UserPreferencesRepo"
    }

    suspend fun savePreferences(userPrefs: UserPreferences) {
        dataStore.edit { preferences ->
            preferences[NOMBRE_USUARIO] = userPrefs.nombreUsario
            preferences[MODO_VISUALIZACION] = userPrefs.modoVisualizacion
        }
    }

    val userPrefs: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val nombreUsuario = preferences[NOMBRE_USUARIO] ?: ""
            val modoVisualizacion = preferences[MODO_VISUALIZACION] ?: ModoVisualizacion.SYSTEM.modo
            UserPreferences(nombreUsuario, modoVisualizacion)
        }
}