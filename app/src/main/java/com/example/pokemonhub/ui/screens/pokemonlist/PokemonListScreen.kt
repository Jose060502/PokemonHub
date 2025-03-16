package com.example.pokemonhub.ui.screens.pokemonlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pokemonhub.R
import com.example.pokemonhub.model.Pokemon
import com.example.pokemonhub.ui.components.LoadingList
import com.example.pokemonhub.ui.components.LoadingListTablet
import com.example.pokemonhub.ui.components.MedHeaderComp
import com.example.pokemonhub.ui.components.MedHeaderCompWithIcon
import com.example.pokemonhub.ui.components.PokemonCard
import com.example.pokemonhub.ui.components.PokemonLandCard
import com.example.pokemonhub.ui.components.SearchBarCustom
import com.example.pokemonhub.ui.screens.favouritelist.PokemonFavListViewModel

@Composable
fun PokemonListCompactScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    pokemonListVM: PokemonListViewModel = viewModel(factory = PokemonListViewModel.Factory),
    favListViewModel: PokemonFavListViewModel = viewModel(factory = PokemonFavListViewModel.Factory)
) {
    val uiState by pokemonListVM.uiState.collectAsState()
    val filteredPokemons by pokemonListVM.filteredPokemon.collectAsState()
    val searchQuery by pokemonListVM.searchQuery.collectAsState()
    var textFieldVisible by remember { mutableStateOf(false) }
    val favoritesState = remember { mutableStateMapOf<String, Boolean>() }

    Column(modifier = modifier.fillMaxSize()) {
        // Cabecera con el título y el ícono de búsqueda
        MedHeaderCompWithIcon(
            title = stringResource(id = R.string.pokemon_list),
            onSearchClick = { textFieldVisible = !textFieldVisible }
        )

        // Mostrar barra de búsqueda si está visible
        if (textFieldVisible) {
            SearchBarCustom(
                onQueryChange = { newText -> pokemonListVM.onSearchQueryChanged(newText) },
                textoIngresado = searchQuery,
            )
        }

        // Mostrar mensaje de error si hay alguno
        uiState.errorMessage?.let {
            Text(
                text = stringResource(R.string.errorLoading),
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }

        // Si no hay Pokémon cargados, cargar los Pokémon
        if (uiState.pokemon.isEmpty() && uiState.isLoading.not()) {
            pokemonListVM.cargarPokemon()
        }

        // Si se está cargando, mostrar indicador de carga
        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LoadingList()
            }
        } else {
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
                // Iterar sobre los Pokémon filtrados y mostrar cada uno
                items(filteredPokemons) { pokemon ->
                    PokemonCard(
                        pokemon = pokemon,
                        onClick = {
                            // Navegar a la pantalla de detalles del Pokémon
                            navController.navigate("pokemon_detail/${pokemon.name}")
                        },
                        favListViewModel = favListViewModel
                    )
                }
            }
        }
    }
}


@Composable
fun PokemonListMedExpScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    pokemonListVM: PokemonListViewModel = viewModel(factory = PokemonListViewModel.Factory),
    favListViewModel: PokemonFavListViewModel = viewModel(factory = PokemonFavListViewModel.Factory)
) {
    var textFieldVisible by remember { mutableStateOf(false) }
    val searchQuery by pokemonListVM.searchQuery.collectAsState()
    val filteredPokemon by pokemonListVM.filteredPokemon.collectAsState()
    val uiState by pokemonListVM.uiState.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        MedHeaderCompWithIcon(
            title = stringResource(id = R.string.pokemon_list),
            onSearchClick = { textFieldVisible = !textFieldVisible }
        )

        // Si la barra de búsqueda está visible
        if (textFieldVisible) {
            SearchBarCustom(
                onQueryChange = { newText -> pokemonListVM.onSearchQueryChanged(newText) },
                textoIngresado = searchQuery,
            )
        }

        // Si los datos están cargando, mostrar un indicador de carga
        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LoadingListTablet()
            }
        } else {
            // Mostrar la lista de Pokémon si no está cargando
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                items(filteredPokemon) { pokemon ->
                    PokemonLandCard(
                        pokemon = pokemon,
                        onClick = { navController.navigate("pokemon_detail/${pokemon.name}") },
                        favListViewModel = favListViewModel
                    )
                }
            }
        }
    }
}


