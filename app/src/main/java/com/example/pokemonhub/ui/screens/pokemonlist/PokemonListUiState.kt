package com.example.pokemonhub.ui.screens.pokemonlist

import com.example.pokemonhub.model.Pokemon

data class PokemonListUiState(
    val pokemon: List<Pokemon> = emptyList(),
    val pokemonSelector: List<String> = emptyList(),
    val errorMessage: ErrorMessage? = null,
    val isLoading : Boolean = false
)

enum class ErrorMessage{
    ERROR_LOADING_LIST
}