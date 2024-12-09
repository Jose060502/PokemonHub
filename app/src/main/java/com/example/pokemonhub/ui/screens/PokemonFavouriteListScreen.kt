package com.example.pokemonhub.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pokemonhub.R
import com.example.pokemonhub.model.Datasource
import com.example.pokemonhub.model.Pokemon
import com.example.pokemonhub.ui.components.MedHeaderComp
import com.example.pokemonhub.ui.components.PokemonCard
import com.example.pokemonhub.ui.components.PokemonFavouriteCard
import com.example.pokemonhub.ui.components.PokemonFavouriteLandCard
import com.example.pokemonhub.ui.components.PokemonLandCard

fun getFavoritePokemons(): List<Pokemon> {
    return listOf(
        Datasource.getPokemonByName("Bulbasaur")!!,
        Datasource.getPokemonByName("Charmander")!!,
        Datasource.getPokemonByName("Squirtle")!!,
        Datasource.getPokemonByName("Zapdos")!!,
        Datasource.getPokemonByName("Mewtwo")!!,
        Datasource.getPokemonByName("Charizard")!!,
    )
}

@Composable
fun PokemonFavouriteListCompactScreen(modifier: Modifier = Modifier) {
    // Obtener los 4 Pokémon favoritos
    val favoritePokemon = getFavoritePokemons()

    // Layout de la pantalla
    Column(modifier = modifier.fillMaxSize()) {
        // Título de la pantalla
        MedHeaderComp(title = stringResource(id = R.string.pokemon_favorite_list))

        // Usamos LazyColumn para mejorar el rendimiento al mostrar los Pokémon
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            items(favoritePokemon) { pokemon ->
                // Mostramos la tarjeta de cada Pokémon
                PokemonFavouriteCard(pokemon)
            }
        }
    }
}

@Composable
fun PokemonFavouriteListMedExpScreen(modifier: Modifier = Modifier){
    val favoritePokemon = getFavoritePokemons()
    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            items(favoritePokemon){ pokemon ->
                PokemonFavouriteLandCard(pokemon)
            }
        }
    }
}