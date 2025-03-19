package com.example.pokemonhub.ui.screens.pokemonlist

import com.example.pokemonhub.model.Pokemon

// Clase que representa el estado de la UI en la pantalla de la lista de Pokémon
data class PokemonListUiState(
    val pokemon: List<Pokemon> = emptyList(), // Lista de Pokémon recuperados
    val pokemonSelector: List<String> = emptyList(), // Lista de nombres de Pokémon para selección
    val errorMessage: ErrorMessage? = null, // Mensaje de error en caso de fallo
    val isLoading : Boolean = false // Indica si la lista está cargando
)

// Enumeración para definir posibles errores en la carga de la lista de Pokémon
enum class ErrorMessage {
    ERROR_LOADING_LIST
}