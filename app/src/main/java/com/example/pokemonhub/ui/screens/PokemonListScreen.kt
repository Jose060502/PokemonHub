package com.example.pokemonhub.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokemonhub.R
import com.example.pokemonhub.model.Pokemon
import com.example.pokemonhub.ui.components.MedHeaderComp
import com.example.pokemonhub.ui.components.PokemonCard
import com.example.pokemonhub.ui.components.PokemonLandCard

@Composable
fun PokemonListCompactScreen(
    pokemon: List<Pokemon>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val favoritesState = remember { mutableStateMapOf<String, Boolean>() }

    Column(modifier = modifier.fillMaxSize()) {
        MedHeaderComp(title = stringResource(id = R.string.pokemon_list))
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            items(pokemon) { pokemon ->
                PokemonCard(
                    pokemon = pokemon,
                    isFavorite = favoritesState[pokemon.name] == true,  // Chequeamos si está en favoritos
                    onFavoriteClick = { isFavorite ->
                        // Cambiamos el estado de favorito cuando el icono sea clickeado
                        favoritesState[pokemon.name] = isFavorite
                    },
                    onClick = {
                        // Navegar a la pantalla de detalles del Pokémon
                        navController.navigate("pokemon_detail/${pokemon.name}")
                    }
                )
            }
        }
    }
}

@Composable
fun PokemonListMedExpScreen(pokemon: List<Pokemon>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            items(pokemon) { pokemon ->
                PokemonLandCard(pokemon)
            }
        }
    }
}

