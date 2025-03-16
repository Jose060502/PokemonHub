package com.example.pokemonhub.pokemonRelease

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.pokemonhub.api.PokemonApiConfig
import com.example.pokemonhub.api.PokemonApiService
import com.example.pokemonhub.data.UserPreferences
import com.example.pokemonhub.data.UserPreferencesRepository
import com.example.pokemonhub.localdatabase.PokemonDatabase
import com.example.pokemonhub.repository.CommentRepository
import com.example.pokemonhub.repository.FavoriteListRepository
import com.example.pokemonhub.repository.PokemonRepository
import com.example.pokemonhub.BuildConfig



val Context.dataStore by preferencesDataStore(name = UserPreferences.SETTINGS_FILE)
class PokedexReleaseApplication : Application() {
    lateinit var userPreferencesRepository: UserPreferencesRepository
    lateinit var listRepository: FavoriteListRepository
    lateinit var commentRepository: CommentRepository
    override fun onCreate() {
        super.onCreate()
        userPreferencesRepository = UserPreferencesRepository(dataStore)
        val baseUrl = BuildConfig.BASE_URL
        val pokemonApiService = PokemonApiConfig.provideRetrofit(baseUrl).create(PokemonApiService::class.java)
        listRepository = FavoriteListRepository(PokemonDatabase.getDatabase(this).ListDAO())
        commentRepository = CommentRepository(PokemonDatabase.getDatabase(this).commentDAO())
    }
    val pokemonRepository: PokemonRepository by lazy {
        PokemonRepository(
            PokemonApiConfig.provideRetrofit(BuildConfig.BASE_URL + "/").create(PokemonApiService::class.java)
        )
    }

}