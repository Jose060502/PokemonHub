package com.example.pokemonhub.repository

import com.example.pokemonhub.api.PokemonApiService
import com.example.pokemonhub.datamodel.FullPokemon
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

// Repositorio encargado de manejar la obtención de datos desde la API
class PokemonRepository(private val apiService: PokemonApiService) {

    companion object {
        const val NUM_CHARACTERS = 1025
    }

    // Función suspendida para obtener la lista de todos los Pokémon con detalles
    suspend fun getAllPokemon(): Result<List<FullPokemon>> {
        return try {
            val response = apiService.getAllPokemon() // Llamada a la API para obtener la lista básica

            if (response.isSuccessful) {
                val pokemonList = response.body()?.results ?: emptyList() // Lista de Pokémon obtenida

                val batchSize = 20 // Se procesan en lotes de 20 para optimizar llamadas a la API
                val detailedPokemonList = coroutineScope {
                    pokemonList.chunked(batchSize).mapIndexed { batchIndex, batch ->
                        async {
                            batch.mapIndexedNotNull { index, _ ->
                                val id = (batchIndex * batchSize) + index + 1 // Asigna ID basado en la posición
                                val fullPokemonResponse = apiService.getPokemonById(id) // Obtiene detalles
                                fullPokemonResponse.body().takeIf { fullPokemonResponse.isSuccessful } // Retorna solo si la respuesta es válida
                            }
                        }
                    }.awaitAll().flatten() // Espera todas las llamadas y aplana la lista
                }

                Result.success(detailedPokemonList) // Retorna la lista completa de Pokémon con detalles
            } else {
                Result.failure(Exception("Error ${response.code()}: ${response.message()}")) // Manejo de error en la API
            }
        } catch (e: Exception) {
            Result.failure(e) // Captura y retorna cualquier excepción
        }
    }
}