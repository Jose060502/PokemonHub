package com.example.pokemonhub.repository

import com.example.pokemonhub.api.PokemonApiService
import com.example.pokemonhub.datamodel.FullPokemon

class PokemonRepository (private val apiService: PokemonApiService) {
    companion object {
        const val NUM_CHARACTERS = 151
    }

    suspend fun getAllPokemon(): Result<List<FullPokemon>?> {
        return try {
            val response = apiService.getAllPokemon()
            if(response.isSuccessful) {
                Result.success(response.body()?.results ?: emptyList())
            } else {
                Result.failure(Exception("Error ${response.code()}: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}