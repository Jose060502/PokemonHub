package com.example.pokemonhub.datamodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// **Entidad de base de datos para almacenar Pokémon favoritos**
// Se usa @Entity para definir que esta clase representa una tabla en Room

@Entity(tableName = "pokefavoritelist")
data class PokeModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // ID autogenerado de cada entrada en la base de datos

    @ColumnInfo(name = "pokemonId")
    val pokemonId: Int? = null, // ID real del Pokémon en la API (puede ser nulo)

    @ColumnInfo(name = "name")
    val name: String? = null, // Nombre del Pokémon

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String? = null, // URL de la imagen del Pokémon

    @ColumnInfo(name = "color")
    val colorHex: String? = null, // Código de color en formato hexadecimal (para UI)

    @ColumnInfo(name = "types")
    val types: List<String>, // Lista de tipos del Pokémon (ejemplo: ["Fuego", "Volador"])

    @ColumnInfo(name = "abilities")
    val abilities: List<String>, // Lista de habilidades del Pokémon

    @ColumnInfo(name = "stats")
    val stats: List<String> // Lista de estadísticas en formato de texto (ejemplo: ["Ataque: 75", "Defensa: 60"])
)

