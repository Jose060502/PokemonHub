package com.example.pokemonhub.ui.screens.profilescreen

import com.example.pokemonhub.data.ModoVisualizacion

// Representa el estado de la UI para la pantalla de perfil
data class ProfileUiState(
    val nombreUsuario: String = "", // Nombre del usuario, vacío por defecto
    val modoVisualizacionApp: ModoVisualizacion = ModoVisualizacion.SYSTEM, // Modo de visualización del tema (Ej: Claro, Oscuro, Sistema)
    val userMessage: UserMessage? = null // Mensaje de error opcional, usado para manejar errores en la UI
)

// Enum para representar posibles mensajes de error en la pantalla de perfil
enum class UserMessage {
    ERROR_ACCESSING_DATASTORE, // Error al acceder a Datastore (lectura)
    ERROR_WRITING_DATASTORE, // Error al escribir en Datastore (guardar preferencias)
}