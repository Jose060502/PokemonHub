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
    private val dataStore: DataStore<Preferences> // DataStore para almacenar las preferencias
) {
    // **Constantes utilizadas en DataStore**
    private companion object {
        val NOMBRE_USUARIO = stringPreferencesKey("nombre_usuario") // Clave para el nombre de usuario
        val MODO_VISUALIZACION = stringPreferencesKey("modo_visualizacion") // Clave para el modo de visualización
        const val TAG = "UserPreferencesRepo" // Etiqueta para logs
    }

    // **Función para guardar las preferencias del usuario en DataStore**
    suspend fun savePreferences(userPrefs: UserPreferences) {
        dataStore.edit { preferences ->
            preferences[NOMBRE_USUARIO] = userPrefs.nombreUsario // Guarda el nombre de usuario
            preferences[MODO_VISUALIZACION] = userPrefs.modoVisualizacionApp // Guarda el modo de visualización
        }
    }

    // **Flow que obtiene las preferencias del usuario en tiempo real**
    val userPrefs: Flow<UserPreferences> = dataStore.data
        .catch { exception -> // Manejo de errores al leer las preferencias
            if (exception is IOException) { // Si es un error de lectura
                Log.e(TAG, "Error reading preferences.", exception)
                emit(emptyPreferences()) // Emite preferencias vacías para evitar fallos
            } else {
                throw exception // Relanza excepciones no controladas
            }
        }
        .map { preferences -> // Transforma los datos obtenidos en un objeto UserPreferences
            val nombreUsuario = preferences[NOMBRE_USUARIO] ?: "" // Recupera el nombre de usuario o vacío
            val modoVisualizacion = preferences[MODO_VISUALIZACION] ?: ModoVisualizacion.SYSTEM.modo // Recupera el modo de visualización o el predeterminado
            UserPreferences(nombreUsuario, modoVisualizacion) // Devuelve el objeto con los valores
        }
}