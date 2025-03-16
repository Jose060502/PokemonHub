package com.example.pokemonhub.repository

import com.example.pokemonhub.datamodel.ListModel
import com.example.pokemonhub.localdatabase.ListDAO
import kotlinx.coroutines.flow.Flow

class FavoriteListRepository(
    private val listDAO: ListDAO
): ListInterface {

    override suspend fun insert(character: ListModel) = listDAO.insert(character)
    override suspend fun delete(character: ListModel) = listDAO.delete(character)
    override val getAllPokemons: Flow<List<ListModel>> = listDAO.getAllPokemons()
}