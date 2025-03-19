package com.example.pokemonhub.ui.screens.favouritelist

import com.example.pokemonhub.datamodel.PokeModel

data class PokemonFavouriteUiState(
    val favorites: List<PokeModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessageFAV: ErrorMessageFAV? = null
)
enum class ErrorMessageFAV{
    ERROR_LOADING_FAVLIST,
    ERROR_INSERTING_FAV,
    ERROR_DELETING_FAV
}