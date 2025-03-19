package com.example.pokemonhub.repository

import com.example.pokemonhub.datamodel.ListModel
import com.example.pokemonhub.localdatabase.PokeDAO
import kotlinx.coroutines.flow.Flow

class FavoriteListRepository(
    private val pokeDAO: PokeDAO
): ListInterface {

    override suspend fun insert(character: ListModel) = pokeDAO.insert(character)
    override suspend fun delete(character: ListModel) = pokeDAO.delete(character)
    override val getAllPokemons: Flow<List<ListModel>> = pokeDAO.getAllPokemons()
}