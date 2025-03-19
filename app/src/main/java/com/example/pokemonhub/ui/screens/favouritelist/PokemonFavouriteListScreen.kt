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
import androidx.compose.runtime.saveable.rememberSaveable
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


// Composable que muestra la lista de Pokémon favoritos en vista compacta
@Composable
fun PokemonFavouriteListCompactScreen(
    navController: NavController, // Controlador de navegación para manejar la navegación entre pantallas
    modifier: Modifier = Modifier,
    viewModel: PokemonFavListViewModel = viewModel(factory = PokemonFavListViewModel.Factory) // ViewModel que gestiona la lista de favoritos
) {
    var showDialog by remember { mutableStateOf(false) }  // Estado para controlar la visibilidad del diálogo de confirmación de eliminación
    var pokemonNameSelected by remember { mutableStateOf("") }  // Estado para almacenar el nombre del Pokémon seleccionado para eliminar
    val uiState by viewModel.uiState.collectAsState()  // Estado de la UI obtenido del ViewModel
    val pokemon = uiState.favorites  // Lista de Pokémon favoritos

    // Si showDialog es true, se muestra el diálogo de confirmación de eliminación
    if (showDialog) {
        ConfirmDeleteDialog(
            pokemonName = pokemonNameSelected,
            onCancel = { showDialog = false },  // Cierra el diálogo si el usuario cancela
            onConfirm = {
                // Busca el Pokémon seleccionado en la lista de favoritos
                val favDelete = pokemon.find { it.name == pokemonNameSelected }
                if (favDelete != null) {
                    viewModel.deleteFavourite(favDelete)  // Llama a la función para eliminarlo
                    showDialog = false  // Cierra el diálogo tras la confirmación
                }
            }
        )
    }

    // Estructura de la pantalla
    Column(modifier = modifier.fillMaxSize()) {
        MedHeaderComp(title = stringResource(id = R.string.fav_characters))  // Encabezado de la pantalla

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)  // Color de fondo
        ) {
            // Recorre la lista de Pokémon favoritos y los muestra en una lista
            items(pokemon) { pokemon ->
                PokemonFavouriteCard(
                    pokemon = pokemon,
                    onClick = { navController.navigate("pokemonfav_detail/${pokemon.name}") } // Navega a la pantalla de detalles del Pokémon
                ) {
                    // Guarda el nombre del Pokémon seleccionado y muestra el diálogo de confirmación
                    pokemonNameSelected = pokemon.name!!
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
    var showDialog by remember { mutableStateOf(false) }  // Estado para controlar el diálogo de confirmación
    var pokemonNameSelected by remember { mutableStateOf("") }  // Nombre del Pokémon seleccionado para eliminar
    val uiState by pokemonListViewModel.uiState.collectAsState()  // Estado de UI desde ViewModel
    val pokemonList = uiState.favorites  // Lista de Pokémon favoritos

    // Si showDialog es true, muestra el diálogo de confirmación de eliminación
    if (showDialog) {
        ConfirmDeleteDialog(
            pokemonName = pokemonNameSelected,
            onCancel = { showDialog = false },  // Cierra el diálogo si el usuario cancela
            onConfirm = {
                // Busca el Pokémon seleccionado en la lista
                val favDelete = pokemonList.find { it.name == pokemonNameSelected }
                if (favDelete != null) {
                    pokemonListViewModel.deleteFavourite(favDelete)  // Llama a la función para eliminarlo
                    showDialog = false  // Cierra el diálogo tras la confirmación
                }
            }
        )
    }

    // Estructura de la pantalla
    Column(modifier = modifier.fillMaxSize()) {
        MedHeaderComp(title = stringResource(id = R.string.fav_characters))  // Encabezado de la pantalla

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)  // Color de fondo
        ) {
            items(pokemonList) { pokemon ->
                PokemonFavouriteLandCard(
                    pokemon = pokemon,
                    onClick = { navController.navigate("pokemonfav_detail/${pokemon.name}") } // Navega a la pantalla de detalles del Pokémon
                ) {
                    // Guarda el nombre del Pokémon seleccionado y muestra el diálogo de confirmación
                    pokemonNameSelected = pokemon.name!!
                    showDialog = true
                }

            }
        }
    }
}

