package com.example.pokemonhub.ui.screens.favouritelist

import com.example.pokemonhub.datamodel.PokeModel

// Data class que representa el estado de la UI relacionado con los Pokémon favoritos
data class PokemonFavouriteUiState(
    // Lista de Pokémon favoritos. Por defecto es una lista vacía.
    val favorites: List<PokeModel> = emptyList(),
    // Indica si los datos están siendo cargados. Inicialmente está en false (no cargando).
    val isLoading: Boolean = false,
    // Representa posibles errores al manejar los favoritos. Por defecto es null, lo que indica que no hay error.
    val errorMessageFAV: ErrorMessageFAV? = null
)

// Enum que define los posibles mensajes de error que pueden ocurrir con los favoritos.
enum class ErrorMessageFAV{
    ERROR_LOADING_FAVLIST,
    ERROR_INSERTING_FAV,
    ERROR_DELETING_FAV
}