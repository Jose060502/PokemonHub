package com.example.pokemonhub.datamodel

import androidx.compose.ui.graphics.Color
import com.example.pokemonhub.model.Pokemon
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// **Modelo de datos para representar un Pokémon obtenido de la API**
// Se usa @Serializable para permitir la conversión entre JSON y objetos Kotlin

@Serializable
data class FullPokemon(
    @SerialName("id") val id: Int? = null, // ID del Pokémon (puede ser nulo)
    @SerialName("name") val name: String, // Nombre del Pokémon
    @SerialName("types") val types: List<PokemonTypeWrapper> = emptyList(), // Lista de tipos del Pokémon
    @SerialName("abilities") val abilities: List<PokemonAbilityWrapper> = emptyList(), // Lista de habilidades
    @SerialName("stats") val stats: List<PokemonStatWrapper> = emptyList(), // Estadísticas del Pokémon
    @SerialName("sprites") val sprites: PokemonSprites? = null // Imagen del Pokémon
) {
    // **Convierte un FullPokemon en un objeto más simple de tipo Pokemon**
    fun toPokemon(): Pokemon {
        return Pokemon(
            name = name,
            types = types.map { it.type.name }, // Extrae los nombres de los tipos
            abilities = abilities.map { it.ability.name }, // Extrae los nombres de las habilidades
            stats = stats.map { "${it.stat.name}: ${it.base_stat}" }, // Convierte las estadísticas en texto
            imageUrl = sprites?.front_default ?: "", // Usa la imagen o una cadena vacía si no hay imagen
            colorFondo = getRandomDarkColor() // Asigna un color aleatorio de fondo
        )
    }

    // **Genera un color oscuro aleatorio para la UI**
    private fun getRandomDarkColor(): Color {
        val colors = listOf(
            Color(0xFF9B74FF),
            Color(0xFF8A68FF),
            Color(0xFF7C5DFF),
            Color(0xFF7A6CFF),
            Color(0xFF5E67FF),
        )
        return colors.random()
    }
}

// **Clases auxiliares para estructurar la respuesta de la API**

// Envoltura del tipo de Pokémon
@Serializable
data class PokemonTypeWrapper(
    @SerialName("type") val type: PokemonType
)

// Tipo de Pokémon (ej: Agua, Fuego, Planta)
@Serializable
data class PokemonType(
    @SerialName("name") val name: String
)

// Envoltura de habilidades del Pokémon
@Serializable
data class PokemonAbilityWrapper(
    @SerialName("ability") val ability: PokemonAbility
)

// Habilidad del Pokémon (ej: Absorber agua, Intimidación)
@Serializable
data class PokemonAbility(
    @SerialName("name") val name: String
)

// Datos de los sprites/imágenes del Pokémon
@Serializable
data class PokemonSprites(
    @SerialName("front_default") val front_default: String? // Imagen principal
)

// **Estructura de la respuesta de la API para obtener varios Pokémon**
@Serializable
data class PokemonResponse(
    val results: List<FullPokemon> // Lista de Pokémon devueltos por la API
)

// Envoltura de estadísticas del Pokémon
@Serializable
data class PokemonStatWrapper(
    @SerialName("base_stat") val base_stat: Int, // Valor numérico de la estadística
    @SerialName("stat") val stat: PokemonStats // Tipo de estadística
)

// Nombre de la estadística del Pokémon (ej: Ataque, Defensa, Velocidad)
@Serializable
data class PokemonStats(
    @SerialName("name") val name: String
)


