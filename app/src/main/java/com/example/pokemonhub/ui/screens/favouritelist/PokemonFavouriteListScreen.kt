package com.example.pokemonhub.ui.screens.favouritelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pokemonhub.R
import com.example.pokemonhub.ui.components.ConfirmDeleteDialog
import com.example.pokemonhub.ui.components.MedHeaderComp
import com.example.pokemonhub.ui.components.PokemonFavouriteCard
import com.example.pokemonhub.ui.components.PokemonFavouriteLandCard


@Composable
fun PokemonFavouriteListCompactScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PokemonFavListViewModel = viewModel(factory = PokemonFavListViewModel.Factory)
) {
    var showDialog by remember { mutableStateOf(false) }
    var pokemonNameSelected by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()
    val pokemon = uiState.favorites

    if (showDialog) {
        ConfirmDeleteDialog(pokemonName = pokemonNameSelected,
            onCancel = { showDialog = false },
            onConfirm = {
                val favDelete = pokemon.find { it.name == pokemonNameSelected }
                if (favDelete != null) {
                    viewModel.deleteFavourite(favDelete)
                    showDialog = false
                }
            })
    }
    Column(modifier = modifier.fillMaxSize()) {
        MedHeaderComp(title = stringResource(id = R.string.fav_characters))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            items(pokemon) { character ->
                PokemonFavouriteCard(pokemon = character,
                    onClick = { navController.navigate("pokemonfav_detail/${character.name}") }) {

                    pokemonNameSelected = character.name!!
                    showDialog = true
                }
            }
        }
    }
}

@Composable
fun PokemonFavouriteListMedExpScreen(
    navController: NavController,
    pokemonListViewModel: PokemonFavListViewModel = viewModel(factory = PokemonFavListViewModel.Factory),
    modifier: Modifier = Modifier
) {
    val uiState by pokemonListViewModel.uiState.collectAsState()
    val pokemonList = uiState.favorites

    Column(modifier = modifier.fillMaxSize()) {
        MedHeaderComp(title = stringResource(id = R.string.fav_characters))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            items(pokemonList) { pokemonList ->
                PokemonFavouriteLandCard(pokemonList) {
                    navController.navigate("pokemonfav_detail/${pokemonList.name}")
                }
            }
        }
    }
}