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


class ProfileViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PokedexReleaseApplication)
                ProfileViewModel(application.userPreferencesRepository)
            }
        }
    }

    private val _uiState = MutableStateFlow(
        ProfileUiState()
    )
    val uiState: StateFlow<ProfileUiState> = _uiState

    fun setSettings(nombreUsuario: String, modoVisualizacion: ModoVisualizacion){
        viewModelScope.launch {
            try {
                userPreferencesRepository.savePreferences(
                    UserPreferences(
                        nombreUsario = nombreUsuario,
                        modoVisualizacion = modoVisualizacion.name,
                    )
                )
            } catch (e: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(userMessage = UserMessage.ERROR_WRITING_DATASTORE)
                }
            }
        }
    }
    init {
        viewModelScope.launch {
            userPreferencesRepository.userPrefs
                .catch { _ ->
                    _uiState.update { currentState ->
                        currentState.copy(userMessage = UserMessage.ERROR_ACCESSING_DATASTORE)
                    }
                }
                .collect { preferences ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            nombreUsuario = preferences.nombreUsario,
                            modoVisualizacion = try {
                                ModoVisualizacion.valueOf(preferences.modoVisualizacion)
                            } catch (e: Exception) {
                                ModoVisualizacion.SYSTEM
                            }
                        )
                    }
                }
        }
    }
}