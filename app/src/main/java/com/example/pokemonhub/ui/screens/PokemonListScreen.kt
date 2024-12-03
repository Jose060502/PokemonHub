package com.example.pokemonhub.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokemonhub.R
import com.example.pokemonhub.model.Datasource
import com.example.pokemonhub.model.Pokemon
import com.example.pokemonhub.ui.components.MedHeaderComp
import com.example.pokemonhub.ui.components.PokemonCard
import com.example.pokemonhub.ui.components.PokemonLandCard

@Composable
fun PokemonListCompactScreen(pokemon: MutableList<Pokemon>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        MedHeaderComp(title = stringResource(id = R.string.pokemon_list))
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            items(pokemon) { pokemon ->
                PokemonCard(pokemon)
            }
        }
    }
}

@Composable
fun PokemonListMedExpScreen(pokemon: MutableList<Pokemon>, modifier: Modifier = Modifier){
    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            items(pokemon){ pokemon ->
                PokemonLandCard(pokemon)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonListScreenPreview() {
    PokemonListCompactScreen(Datasource.pokemonList())
}