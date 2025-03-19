package com.example.pokemonhub.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pokemonhub.R
import com.example.pokemonhub.datamodel.PokeModel
import com.example.pokemonhub.ui.components.LoadingDetail
import com.example.pokemonhub.ui.components.NotFoundDetail
import com.example.pokemonhub.ui.components.PokemonDetailCard
import com.example.pokemonhub.ui.components.PokemonDetailCardLand
import com.example.pokemonhub.ui.screens.favouritelist.PokemonFavListViewModel
import com.example.pokemonhub.ui.screens.pokemonlist.PokemonListViewModel

@Composable
fun PokemonDetailsListCompactScreen(
    name: String,
    navController: NavController,
    modifier: Modifier = Modifier,
    pokemonListVM: PokemonListViewModel = viewModel(factory = PokemonListViewModel.Factory),
    favListViewModel: PokemonFavListViewModel = viewModel(factory = PokemonFavListViewModel.Factory)
) {

    val uiState = pokemonListVM.uiState.collectAsState().value
    val favUiState by favListViewModel.uiState.collectAsState()
    val pokemon = uiState.pokemon.find { it.name.equals(name, ignoreCase = true) }
    val isAlreadyFavorite = pokemon?.let { poke ->
        favUiState.favorites.any { it.name == poke.name }
    } ?: false

    val alreadyfavorite = stringResource(R.string.already)
    val favoriteadd = stringResource(R.string.addfavorite)
    val context = LocalContext.current

    if (uiState.pokemon.isEmpty()) {
        LoadingDetail(name)
        return
    }

    if (pokemon != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            PokemonDetailCard(
                pokemon = pokemon,
                onFavoriteClick = {
                    if (isAlreadyFavorite) {
                        Toast.makeText(context, alreadyfavorite, Toast.LENGTH_SHORT).show()
                    } else {
                        val hexColor = String.format("#%08X", pokemon.colorFondo.toArgb())
                        val favoriteItem = PokeModel(
                            name = pokemon.name,
                            imageUrl = pokemon.imageUrl,
                            colorHex = hexColor,
                            types = pokemon.types,
                            abilities = pokemon.abilities,
                            stats = pokemon.stats
                        )
                        favListViewModel.insertFavourite(favoriteItem)
                        Toast.makeText(context, favoriteadd, Toast.LENGTH_SHORT).show()
                    }
                },
                onBackClick = { navController.navigateUp() }
            )
        }
    } else {
        NotFoundDetail(name)
    }
}

@Composable
fun DetailPokemonScreenMedium(
    name: String,
    navController: NavController,
    modifier: Modifier = Modifier,
    pokemonListVM: PokemonListViewModel = viewModel(factory = PokemonListViewModel.Factory),
    favListViewModel: PokemonFavListViewModel = viewModel(factory = PokemonFavListViewModel.Factory)
) {

    val uiState = pokemonListVM.uiState.collectAsState().value
    val favUiState by favListViewModel.uiState.collectAsState()

    val pokemon = uiState.pokemon.find { it.name.equals(name, ignoreCase = true) }

    val isAlreadyFavorite = pokemon?.let { poke ->
        favUiState.favorites.any { it.name == poke.name }
    } ?: false

    val alreadyfavorite = stringResource(R.string.already)
    val favoriteadd = stringResource(R.string.addfavorite)
    val context = LocalContext.current

    if (uiState.pokemon.isEmpty()) {
        LoadingDetail(name)
        return
    }

    if (pokemon != null) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background) // Cambié colorFondo por background
        ) {
            item {
                PokemonDetailCardLand(
                    pokemon = pokemon,
                    onFavoriteClick = {
                        if (isAlreadyFavorite) {
                            Toast.makeText(context, alreadyfavorite, Toast.LENGTH_SHORT).show()
                        } else {
                            val hexColor = String.format("#%08X", pokemon.colorFondo.toArgb())
                            val favoriteItem = PokeModel(
                                name = pokemon.name,
                                imageUrl = pokemon.imageUrl,
                                colorHex = hexColor,
                                types = pokemon.types,
                                abilities = pokemon.abilities,
                                stats = pokemon.stats
                            )
                            favListViewModel.insertFavourite(favoriteItem)
                            Toast.makeText(context, favoriteadd, Toast.LENGTH_SHORT).show()
                        }
                    },
                    onBackClick = { navController.navigateUp() }
                )
            }
        }
    } else {
        NotFoundDetail(name)
    }
}
