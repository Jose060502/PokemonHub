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
import com.example.pokemonhub.repository.FavoritePokeRepository
import com.example.pokemonhub.repository.PokemonRepository
import com.example.pokemonhub.BuildConfig



// **Extensión de Context para DataStore**
// - Proporciona acceso centralizado a las preferencias del usuario.
val Context.dataStore by preferencesDataStore(name = UserPreferences.SETTINGS_FILE)

// **Clase principal de la aplicación (Application)**
// - Se encarga de inicializar repositorios y servicios globales al iniciar la app.
class PokedexReleaseApplication : Application() {

    // **Repositorios principales** (MVVM - Capa de datos)
    lateinit var userPreferencesRepository: UserPreferencesRepository  // Preferencias del usuario
    lateinit var listRepository: FavoritePokeRepository  // Gestión de favoritos
    lateinit var commentRepository: CommentRepository  // Gestión de comentarios

    override fun onCreate() {
        super.onCreate()

        // **Inicialización de UserPreferencesRepository** (Almacena datos en DataStore)
        userPreferencesRepository = UserPreferencesRepository(dataStore)

        // **Base URL para la API de Pokémon** (Definida en `BuildConfig.BASE_URL`)
        val baseUrl = BuildConfig.BASE_URL

        // **Creación del servicio API de Pokémon usando Retrofit**
        val pokemonApiService = PokemonApiConfig.provideRetrofit(baseUrl).create(PokemonApiService::class.java)

        // **Inicialización de la base de datos local Room y sus DAOs**
        val database = PokemonDatabase.getDatabase(this)
        listRepository = FavoritePokeRepository(database.pokeDAO())  // DAO de Pokémon favoritos
        commentRepository = CommentRepository(database.commentDAO())  // DAO de comentarios
    }

    // **Repositorio de Pokémon que consume la API**
    // - Se inicializa solo cuando se usa (`lazy` optimiza la carga)
    val pokemonRepository: PokemonRepository by lazy {
        PokemonRepository(
            PokemonApiConfig.provideRetrofit(BuildConfig.BASE_URL + "/").create(PokemonApiService::class.java)
        )
    }
}
