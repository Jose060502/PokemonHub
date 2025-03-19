package com.example.pokemonhub.repository

import com.example.pokemonhub.datamodel.PokeModel
import kotlinx.coroutines.flow.Flow

interface PokeInterface {
    suspend fun insert(character: PokeModel)
    suspend fun delete(character: PokeModel)
    val getAllPokemons: Flow<List<PokeModel>>
}