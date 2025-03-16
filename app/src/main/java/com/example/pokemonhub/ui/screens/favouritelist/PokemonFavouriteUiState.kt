package com.example.pokemonhub.ui.screens.favouritelist

import com.example.pokemonhub.datamodel.ListModel

data class PokemonFavouriteUiState(
    val favorites: List<ListModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessageFAV: ErrorMessageFAV? = null
)
enum class ErrorMessageFAV{
    ERROR_LOADING_FAVLIST,
    ERROR_INSERTING_FAV,
    ERROR_DELETING_FAV
}