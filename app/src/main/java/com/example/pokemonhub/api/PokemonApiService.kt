package com.example.pokemonhub.api

import com.example.pokemonhub.datamodel.FullPokemon
import com.example.pokemonhub.datamodel.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): Response<FullPokemon>

    @GET("pokemon?limit=151")
    suspend fun getAllPokemon(): Response<PokemonResponse>
}