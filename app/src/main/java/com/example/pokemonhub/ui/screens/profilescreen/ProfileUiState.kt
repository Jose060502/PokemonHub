package com.example.pokemonhub.ui.screens.profilescreen

import com.example.pokemonhub.data.ModoVisualizacion

data class ProfileUiState(
    val nombreUsuario : String = "",
    val modoVisualizacion: ModoVisualizacion = ModoVisualizacion.SYSTEM,
    val userMessage: UserMessage? = null
)

enum class UserMessage {
    ERROR_ACCESSING_DATASTORE,
    ERROR_WRITING_DATASTORE,
}