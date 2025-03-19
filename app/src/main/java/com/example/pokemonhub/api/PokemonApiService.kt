package com.example.pokemonhub.api

import com.example.pokemonhub.datamodel.FullPokemon
import com.example.pokemonhub.datamodel.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// Interfaz que define las llamadas a la API de Pokémon usando Retrofit
interface PokemonApiService {

    // Obtiene los detalles de un Pokémon específico por su ID
    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): Response<FullPokemon>

    // Obtiene la lista de todos los Pokémon disponibles (hasta 1025)
    @GET("pokemon?limit=1025")
    suspend fun getAllPokemon(): Response<PokemonResponse>
}