package com.example.pokemonhub.repository

import com.example.pokemonhub.api.PokemonApiService
import com.example.pokemonhub.datamodel.FullPokemon
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

// Repositorio encargado de manejar la obtención de datos desde la API
class PokemonRepository(private val apiService: PokemonApiService) {

    // Constante que define un número de caracteres (probablemente relacionada con la cantidad de Pokémon a manejar)
    companion object {
        const val NUM_CHARACTERS = 1025
    }

    // Función suspendida para obtener la lista de todos los Pokémon con detalles completos
    suspend fun getAllPokemon(): Result<List<FullPokemon>> {
        return try {
            // Llamada a la API para obtener la lista básica de Pokémon (solo nombres y URL)
            val response = apiService.getAllPokemon()

            // Verifica si la respuesta de la API fue exitosa
            if (response.isSuccessful) {
                // Obtiene la lista de Pokémon de la respuesta o una lista vacía si es nula
                val pokemonList = response.body()?.results ?: emptyList()

                // Definir el tamaño de los lotes para optimizar las llamadas a la API
                val batchSize = 20

                // Usamos corutinas para procesar los Pokémon en lotes de 'batchSize'
                val detailedPokemonList = coroutineScope {
                    // Divide la lista de Pokémon en lotes de 20
                    pokemonList.chunked(batchSize).mapIndexed { batchIndex, batch ->
                        // Llamada asincrónica para obtener detalles de cada Pokémon en el lote
                        async {
                            // Para cada Pokémon en el lote, se obtiene su detalle por ID
                            batch.mapIndexedNotNull { index, _ ->
                                // Calcula el ID del Pokémon basado en el índice del lote y el índice dentro de ese lote
                                val id = (batchIndex * batchSize) + index + 1

                                // Llamada a la API para obtener los detalles del Pokémon usando su ID
                                val fullPokemonResponse = apiService.getPokemonById(id)

                                // Devuelve los detalles solo si la respuesta es exitosa
                                fullPokemonResponse.body().takeIf { fullPokemonResponse.isSuccessful }
                            }
                        }
                    }.awaitAll().flatten() // Espera todas las llamadas y aplana la lista de resultados
                }

                // Si la operación fue exitosa, retorna la lista completa de Pokémon con detalles
                Result.success(detailedPokemonList)
            } else {
                // Si la respuesta de la API no fue exitosa, devuelve un error con el código y mensaje de la respuesta
                Result.failure(Exception("Error ${response.code()}: ${response.message()}"))
            }
        } catch (e: Exception) {
            // Si ocurre cualquier excepción durante la ejecución, se captura y devuelve un error con la excepción
            Result.failure(e)
        }
    }
}
