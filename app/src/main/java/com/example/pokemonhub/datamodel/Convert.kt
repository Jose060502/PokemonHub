package com.example.pokemonhub.datamodel

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Convert {
    // Convertidor para List<String>
    @TypeConverter
    fun fromString(value: String?): List<String> {
        if (value.isNullOrBlank()) return emptyList()
        return value.split(",").map { it.trim() }.filter { it.isNotEmpty() }
    }

    @TypeConverter
    fun listToString(list: List<String>): String {
        return list.joinToString(separator = ",")
    }

    // Convertidor para List<PokemonStat> usando kotlinx.serialization
    @TypeConverter
    fun fromPokemonStatList(value: String?): List<PokemonStat> {
        if (value.isNullOrEmpty()) return emptyList()
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun toPokemonStatList(list: List<PokemonStat>): String {
        return Json.encodeToString(list)
    }
}
