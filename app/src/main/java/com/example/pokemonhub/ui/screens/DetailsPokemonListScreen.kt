package com.example.pokemonhub.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pokemonhub.R
import com.example.pokemonhub.datamodel.ListModel
import com.example.pokemonhub.model.Datasource
import com.example.pokemonhub.model.Pokemon
import com.example.pokemonhub.ui.components.ImageComp
import com.example.pokemonhub.ui.components.LoadingDetail
import com.example.pokemonhub.ui.components.MedHeaderComp
import com.example.pokemonhub.ui.components.NotFoundDetail
import com.example.pokemonhub.ui.components.PokemonCard
import com.example.pokemonhub.ui.components.PokemonDetailCard
import com.example.pokemonhub.ui.components.PokemonDetailCardLand
import com.example.pokemonhub.ui.components.PokemonLandCard
import com.example.pokemonhub.ui.components.StandardButtonComp
import com.example.pokemonhub.ui.components.StandardTextComp
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
                        val favoriteItem = ListModel(
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
                            val favoriteItem = ListModel(
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
