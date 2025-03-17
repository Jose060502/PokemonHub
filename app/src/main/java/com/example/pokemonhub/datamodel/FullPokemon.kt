package com.example.pokemonhub.datamodel

import androidx.compose.ui.graphics.Color
import com.example.pokemonhub.model.Pokemon
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FullPokemon(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String,
    @SerialName("types") val types: List<PokemonTypeWrapper> = emptyList(),
    @SerialName("abilities") val abilities: List<PokemonAbilityWrapper> = emptyList(),
    @SerialName("stats") val stats: List<PokemonStatWrapper> = emptyList(),
    @SerialName("sprites") val sprites: PokemonSprites? = null
) {
    fun toPokemon(): Pokemon {
        return Pokemon(
            name = name,
            types = types.map { it.type.name },
            abilities = abilities.map { it.ability.name },
            stats = stats.map { "${it.stat.name}: ${it.base_stat}" },
            imageUrl = sprites?.front_default ?: "",
            colorFondo = getRandomDarkColor()
        )
    }

    private fun getRandomDarkColor(): Color {
        val colors = listOf(
            Color(0xFF6A4CFF),
            Color(0xFF7D53FF),
            Color(0xFF8A63FF),
            Color(0xFF4A7CFF),
            Color(0xFF9A77FF),
        )
        return colors.random()
    }
}

@Serializable
data class PokemonTypeWrapper(
    @SerialName("type") val type: PokemonType
)

@Serializable
data class PokemonType(
    @SerialName("name") val name: String
)

@Serializable
data class PokemonAbilityWrapper(
    @SerialName("ability") val ability: PokemonAbility
)

@Serializable
data class PokemonAbility(
    @SerialName("name") val name: String
)

@Serializable
data class PokemonSprites(
    @SerialName("front_default") val front_default: String?
)

@Serializable
data class PokemonResponse(
    val results: List<FullPokemon>
)


@Serializable
data class PokemonStatWrapper(
    @SerialName("base_stat") val base_stat: Int,
    @SerialName("stat") val stat: PokemonStats
)

@Serializable
data class PokemonStats(
    @SerialName("name") val name: String
)

@Serializable
data class Info(
    val totalPages: Int,
    val count: Int,
    val previousPage: String? = null,
    val nextPage: String? = null
)

