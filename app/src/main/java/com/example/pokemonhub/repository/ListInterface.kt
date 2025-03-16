package com.example.pokemonhub.repository

import com.example.pokemonhub.datamodel.ListModel
import kotlinx.coroutines.flow.Flow

interface ListInterface {
    suspend fun insert(character: ListModel)
    suspend fun delete(character: ListModel)
    val getAllPokemons: Flow<List<ListModel>>
}