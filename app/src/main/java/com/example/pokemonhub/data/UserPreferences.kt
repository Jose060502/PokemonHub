package com.example.pokemonhub.data

data class UserPreferences(
    val nombreUsario: String,
    val modoVisualizacion: String,
)
{
    companion object {
        const val SETTINGS_FILE = "settings_file"
    }
}

//enum class NombreUsuario(val nombreUsuario: String) {
//NOMBREUSUARIO(" ")
//}
enum class ModoVisualizacion(val modo: String) {
    CLARO("LIGHT"),
    OSCURO("DARK"),
    SYSTEM("SYSTEM")
}