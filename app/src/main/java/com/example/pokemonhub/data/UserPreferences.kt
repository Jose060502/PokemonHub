package com.example.pokemonhub.data

data class UserPreferences(
    val nombreUsario: String,       // Nombre del usuario
    val modoVisualizacionApp: String   // Modo de visualización preferido (claro, oscuro o sistema)
) {
    companion object {
        const val SETTINGS_FILE = "settings_file" // Nombre del archivo de configuración
    }
}

// Enumeración que define los modos de visualización disponibles
enum class ModoVisualizacion(val modo: String) {
    CLARO("LIGHT"),   // Modo claro
    OSCURO("DARK"),   // Modo oscuro
    SYSTEM("SYSTEM")  // Modo según la configuración del sistema
}