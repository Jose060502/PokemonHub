package com.example.pokemonhub.ui.screens.pokemonlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pokemonhub.model.Pokemon
import com.example.pokemonhub.pokemonRelease.PokedexReleaseApplication
import com.example.pokemonhub.repository.PokemonRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PokedexReleaseApplication)
                PokemonListViewModel(
                    application.pokemonRepository
                )
            }
        }
    }

    private val _uiState = MutableStateFlow(PokemonListUiState())
    val uiState: StateFlow<PokemonListUiState> = _uiState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        cargarPokemon()
    }

    val filteredPokemon: StateFlow<List<Pokemon>> =
        combine(uiState, searchQuery) { currentState, query ->
            currentState.pokemon.filter { pokemon ->
                pokemon.name.contains(query, ignoreCase = true)
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun onSearchQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun cargarPokemon() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            val result = pokemonRepository.getAllPokemon()
            Log.d("PokemonListViewModel", "API Response: $result")
            if (result.isSuccess) {
                val pokemonList = result.getOrNull().orEmpty().map { it.toPokemon() }
                Log.d("PokemonListViewModel", "Pokemon list loaded: ${pokemonList.size} items")
                _uiState.value = PokemonListUiState(
                    pokemon = pokemonList,
                    isLoading = false
                )
            } else {
                val errorMessage = result.exceptionOrNull()?.message ?: "Error desconocido"
                Log.e("PokemonListViewModel", "Error loading Pokémon: $errorMessage")
                _uiState.value = PokemonListUiState(
                    pokemon = emptyList(),
                    errorMessage = ErrorMessage.ERROR_LOADING_LIST,
                    isLoading = false
                )
            }
        }
    }
}