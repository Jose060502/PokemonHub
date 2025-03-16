package com.example.pokemonhub.api

import com.example.pokemonhub.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class PokemonApiConfig {
    companion object {



        // Define el content type para JSON
        private val contentType = "application/json".toMediaType()

        // Configura el objeto Json con algunas opciones (por ejemplo, ignorar claves desconocidas)
        private val json = Json {
            ignoreUnknownKeys = true
            // Puedes configurar otras opciones según lo necesites
        }

        //Definición de la api de Retrofit2.
        fun provideRetrofit(baseUrl: String) : Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .baseUrl(baseUrl)
                .build()
        }

        fun provideApiService(): PokemonApiService {
            return provideRetrofit(BuildConfig.BASE_URL).create(PokemonApiService::class.java)
        }
    }
}