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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokemonhub.R
import com.example.pokemonhub.model.Datasource
import com.example.pokemonhub.model.Pokemon
import com.example.pokemonhub.ui.components.ConfirmDeleteDialog
import com.example.pokemonhub.ui.components.MedHeaderComp
import com.example.pokemonhub.ui.components.PokemonCard
import com.example.pokemonhub.ui.components.PokemonFavouriteCard
import com.example.pokemonhub.ui.components.PokemonFavouriteLandCard
import com.example.pokemonhub.ui.components.PokemonLandCard



@Composable
fun PokemonFavouriteListCompactScreen(pokemon: MutableList<Pokemon>, modifier: Modifier = Modifier) {
    var showDialog by remember { mutableStateOf(false) }
    var pokemonNameSelected by remember { mutableStateOf("") }
    if (showDialog) {
        ConfirmDeleteDialog(
            pokemonName = pokemonNameSelected,
            onCancel = { showDialog = false },
            onConfirm = {
                pokemon.removeIf { it.name == pokemonNameSelected }
                showDialog = false
            }
        )
    }
    Column(modifier = modifier.fillMaxSize()) {
        MedHeaderComp(title = stringResource(id = R.string.pokemon_favorite_list))
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            items(pokemon) { pokemon ->
                PokemonFavouriteCard(pokemon){
                    pokemonNameSelected = pokemon.name
                    showDialog = true
                }
            }
        }
    }
}

@Composable
fun PokemonFavouriteListMedExpScreen(pokemon: MutableList<Pokemon>,modifier: Modifier = Modifier){
    var showDialog by remember { mutableStateOf(false) }
    var pokemonNameSelected by remember { mutableStateOf("") }
    if (showDialog) {
        ConfirmDeleteDialog(
            pokemonName = pokemonNameSelected,
            onCancel = { showDialog = false },
            onConfirm = {
                pokemon.removeIf { it.name == pokemonNameSelected }
                showDialog = false
            }
        )
    }
    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            items(pokemon){ pokemon ->
                PokemonFavouriteCard(pokemon){
                    pokemonNameSelected = pokemon.name
                    showDialog = true
                }
            }
        }
    }
}