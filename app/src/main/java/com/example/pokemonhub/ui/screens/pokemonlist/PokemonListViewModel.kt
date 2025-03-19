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

// ViewModel que gestiona la lógica y el estado de la UI para la lista de Pokémon
// Sigue la arquitectura MVVM, actuando como intermediario entre la Vista y el Modelo (repositorio)
class PokemonListViewModel(
    private val pokemonRepository: PokemonRepository // Capa de Modelo que obtiene los datos
) : ViewModel() {

    companion object {
        // Fábrica para la creación del ViewModel, necesaria cuando se requiere inyección de dependencias
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PokedexReleaseApplication)
                PokemonListViewModel(
                    application.pokemonRepository // Se obtiene el repositorio desde la aplicación
                )
            }
        }
    }

    // Estado de la UI que almacena la lista de Pokémon y su estado de carga
    private val _uiState = MutableStateFlow(PokemonListUiState())
    val uiState: StateFlow<PokemonListUiState> = _uiState // Exposición del estado de la UI como flujo de solo lectura

    // Estado que almacena el término de búsqueda ingresado por el usuario
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        cargarPokemon() // Se ejecuta la carga inicial de datos cuando se crea el ViewModel
    }

    // Estado derivado que filtra la lista de Pokémon según el término de búsqueda
    val filteredPokemon: StateFlow<List<Pokemon>> =
        combine(uiState, searchQuery) { currentState, query ->
            currentState.pokemon.filter { pokemon ->
                pokemon.name.contains(query, ignoreCase = true) // Filtra los Pokémon por nombre (sin distinción de mayúsculas/minúsculas)
            }
        }.stateIn(
            viewModelScope, // Se mantiene dentro del alcance del ViewModel
            SharingStarted.WhileSubscribed(5000), // Se mantiene activo mientras haya observadores suscritos
            emptyList() // Estado inicial vacío
        )

    // Actualiza el término de búsqueda cuando el usuario introduce un nuevo valor
    fun onSearchQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
    }

    // Función que carga la lista de Pokémon desde el repositorio y actualiza el estado de la UI
    fun cargarPokemon() {
        _uiState.value = _uiState.value.copy(isLoading = true) // Indica que la carga está en proceso

        viewModelScope.launch {
            val result = pokemonRepository.getAllPokemon() // Llamada al repositorio para obtener los datos desde la API
            Log.d("PokemonListViewModel", "API Response: $result")

            if (result.isSuccess) {
                val pokemonList = result.getOrNull().orEmpty().map { it.toPokemon() } // Mapea los datos al modelo de UI
                Log.d("PokemonListViewModel", "Pokemon list loaded: ${pokemonList.size} items")

                // Se actualiza el estado con la lista cargada y se desactiva el indicador de carga
                _uiState.value = PokemonListUiState(
                    pokemon = pokemonList,
                    isLoading = false
                )
            } else {
                // Manejo de error en caso de fallo en la carga de datos
                val errorMessage = result.exceptionOrNull()?.message ?: "Error desconocido"
                Log.e("PokemonListViewModel", "Error loading Pokémon: $errorMessage")

                _uiState.value = PokemonListUiState(
                    pokemon = emptyList(),
                    errorMessage = ErrorMessage.ERROR_LOADING_LIST, // Notifica que hubo un error en la carga
                    isLoading = false
                )
            }
        }
    }
}
