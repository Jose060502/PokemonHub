package com.example.pokemonhub.model

data class Pokemon(
    val name: String,
    val type: String,
    val pokedexNumber : Int,
    val ability : String,
    val hiddenAbility : String,
    val baseHp : Int,
    val baseAttack : Int,
    val baseDefense : Int,
    val baseSpAtk : Int,
    val baseSpDef : Int,
    val baseSpeed : Int,
    val photo : String,
    val description : String
)
