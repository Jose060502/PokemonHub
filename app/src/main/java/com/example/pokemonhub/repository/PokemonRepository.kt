package com.example.pokemonhub.repository

import com.example.pokemonhub.api.PokemonApiService
import com.example.pokemonhub.datamodel.FullPokemon

class PokemonRepository (private val apiService: PokemonApiService) {
    companion object {
        const val NUM_CHARACTERS = 151
    }

    suspend fun getAllPokemon(): Result<List<FullPokemon>> {
        return try {
            val response = apiService.getAllPokemon()

            if (response.isSuccessful) {
                val pokemonList = response.body()?.results ?: emptyList()

                // Obtener detalles completos asignando el ID según la posición en la lista
                val detailedPokemonList = pokemonList.mapIndexedNotNull { index, pokemon ->
                    val id = index + 1  // ID basado en la posición (1 para Bulbasaur, 2 para Ivysaur, etc.)
                    val fullPokemonResponse = apiService.getPokemonById(id)

                    if (fullPokemonResponse.isSuccessful) {
                        fullPokemonResponse.body()
                    } else {
                        null
                    }
                }

                Result.success(detailedPokemonList)
            } else {
                Result.failure(Exception("Error ${response.code()}: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}