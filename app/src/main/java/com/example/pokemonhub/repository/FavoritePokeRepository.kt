package com.example.pokemonhub.repository

import com.example.pokemonhub.datamodel.PokeModel
import com.example.pokemonhub.localdatabase.PokeDAO
import kotlinx.coroutines.flow.Flow

class FavoritePokeRepository(
    private val pokeDAO: PokeDAO
): PokeInterface {

    override suspend fun insert(character: PokeModel) = pokeDAO.insert(character)
    override suspend fun delete(character: PokeModel) = pokeDAO.delete(character)
    override val getAllPokemons: Flow<List<PokeModel>> = pokeDAO.getAllPokemons()
}