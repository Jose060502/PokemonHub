package com.example.pokemonhub.datamodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "favoritelist")
data class PokeModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "pokemonId")
    val pokemonId: Int? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String? = null,

    @ColumnInfo(name = "color")
    val colorHex: String? = null,

    @ColumnInfo(name = "types")
    val types: List<String>,

    @ColumnInfo(name = "abilities")
    val abilities: List<String>,

    @ColumnInfo(name = "stats")
    val stats: List<String>
)

@Serializable
data class PokemonStat(
    @SerialName("stat") val stat: StatDetail,
    @SerialName("base_stat") val baseStat: Int
)

@Serializable
data class StatDetail(
    @SerialName("name") val name: String
)