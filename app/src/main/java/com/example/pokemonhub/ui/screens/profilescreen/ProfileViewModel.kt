package com.example.pokemonhub.ui.screens.profilescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pokemonhub.data.UserPreferencesRepository
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import com.example.pokemonhub.data.ModoVisualizacion
import com.example.pokemonhub.data.UserPreferences
import com.example.pokemonhub.pokemonRelease.PokedexReleaseApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


// ViewModel para gestionar el estado y la lógica de la pantalla de perfil
class ProfileViewModel(
    private val userPreferencesRepository: UserPreferencesRepository // Repositorio para gestionar las preferencias del usuario
) : ViewModel() {

    // Fábrica para crear una instancia de ProfileViewModel, necesaria para poder inyectar dependencias correctamente
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PokedexReleaseApplication)
                ProfileViewModel(application.userPreferencesRepository)
            }
        }
    }

    // Estado interno del ViewModel utilizando StateFlow para que la UI pueda observar cambios
    private val _uiState = MutableStateFlow(
        ProfileUiState()
    )
    val uiState: StateFlow<ProfileUiState> = _uiState // Estado expuesto a la UI

    // Función para actualizar las preferencias del usuario y guardarlas en Datastore
    fun setSettings(nombreUsuario: String, modoVisualizacion: ModoVisualizacion) {
        viewModelScope.launch {
            try {
                userPreferencesRepository.savePreferences(
                    UserPreferences(
                        nombreUsario = nombreUsuario, // Guarda el nombre del usuario
                        modoVisualizacionApp = modoVisualizacion.name, // Guarda el modo de visualización como string
                    )
                )
            } catch (e: Exception) {
                // En caso de error al escribir en Datastore, actualizar el estado con un mensaje de error
                _uiState.update { currentState ->
                    currentState.copy(userMessage = UserMessage.ERROR_WRITING_DATASTORE)
                }
            }
        }
    }

    // Inicializador del ViewModel: carga las preferencias del usuario al iniciarse
    init {
        viewModelScope.launch {
            userPreferencesRepository.userPrefs.catch { _ -> // Captura errores si hay problemas al acceder a Datastore
                    _uiState.update { currentState ->
                        currentState.copy(userMessage = UserMessage.ERROR_ACCESSING_DATASTORE)
                    }
                }
                .collect { preferences -> // Observa los cambios en las preferencias y actualiza el estado
                    _uiState.update { currentState ->
                        currentState.copy(
                            nombreUsuario = preferences.nombreUsario, // Asigna el nombre del usuario desde Datastore
                            modoVisualizacionApp = try {
                                ModoVisualizacion.valueOf(preferences.modoVisualizacionApp) // Convierte el string a enum
                            } catch (e: Exception) {
                                ModoVisualizacion.SYSTEM // Si el valor no es válido, usa el modo por defecto
                            }
                        )
                    }
                }
        }
    }
}
