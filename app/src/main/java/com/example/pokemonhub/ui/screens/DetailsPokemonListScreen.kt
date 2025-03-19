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

// Composable para mostrar los detalles de un Pokémon en una pantalla compacta
@Composable
fun PokemonDetailsListCompactScreen(
    name: String, // Nombre del Pokémon para mostrar los detalles
    navController: NavController, // Controlador de navegación para manejar el back
    modifier: Modifier = Modifier, // Modificador opcional para personalizar la UI
    pokemonListVM: PokemonListViewModel = viewModel(factory = PokemonListViewModel.Factory), // ViewModel para obtener la lista de Pokémon
    favListViewModel: PokemonFavListViewModel = viewModel(factory = PokemonFavListViewModel.Factory) // ViewModel para manejar la lista de favoritos
) {

    // Se obtiene el estado de la UI desde el ViewModel de la lista de Pokémon
    val uiState = pokemonListVM.uiState.collectAsState().value
    // Se obtiene el estado de la UI desde el ViewModel de la lista de favoritos
    val favUiState by favListViewModel.uiState.collectAsState()

    // Buscar el Pokémon en la lista por su nombre (ignorando mayúsculas y minúsculas)
    val pokemon = uiState.pokemon.find { it.name.equals(name, ignoreCase = true) }

    // Comprobar si el Pokémon ya está en la lista de favoritos
    val isAlreadyFavorite = pokemon?.let { poke ->
        favUiState.favorites.any { it.name == poke.name }
    } ?: false

    // Mensajes para mostrar en los Toasts
    val alreadyfavorite = stringResource(R.string.already)
    val favoriteadd = stringResource(R.string.addfavorite)

    // Contexto de la aplicación, necesario para mostrar los Toasts
    val context = LocalContext.current

    // Si la lista de Pokémon está vacía, mostramos un indicador de carga
    if (uiState.pokemon.isEmpty()) {
        LoadingDetail(name) // Mostramos una pantalla de carga mientras obtenemos los datos
        return
    }

    // Si se encuentra el Pokémon, mostramos sus detalles
    if (pokemon != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background) // Fondo de la pantalla
        ) {
            // Tarjeta con los detalles del Pokémon
            PokemonDetailCard(
                pokemon = pokemon,
                // Acción para añadir o quitar de favoritos
                onFavoriteClick = {
                    if (isAlreadyFavorite) {
                        // Si ya está en favoritos, mostramos un Toast
                        Toast.makeText(context, alreadyfavorite, Toast.LENGTH_SHORT).show()
                    } else {
                        // Si no está en favoritos, lo agregamos
                        val hexColor = String.format("#%08X", pokemon.colorFondo.toArgb()) // Convertimos el color de fondo en formato hexadecimal
                        val favoriteItem = PokeModel(
                            name = pokemon.name,
                            imageUrl = pokemon.imageUrl,
                            colorHex = hexColor,
                            types = pokemon.types,
                            abilities = pokemon.abilities,
                            stats = pokemon.stats
                        )
                        // Insertamos el Pokémon en la lista de favoritos
                        favListViewModel.insertFavourite(favoriteItem)
                        Toast.makeText(context, favoriteadd, Toast.LENGTH_SHORT).show() // Mostramos un Toast de confirmación
                    }
                },
                // Acción para volver a la pantalla anterior
                onBackClick = { navController.navigateUp() }
            )
        }
    } else {
        // Si no se encuentra el Pokémon, mostramos una pantalla de "No encontrado"
        NotFoundDetail(name)
    }
}

// Composable para mostrar los detalles de un Pokémon en una pantalla más amplia (con Scroll)
@Composable
fun DetailPokemonScreenMedium(
    name: String, // Nombre del Pokémon
    navController: NavController, // Controlador de navegación
    modifier: Modifier = Modifier, // Modificador opcional para personalizar la UI
    pokemonListVM: PokemonListViewModel = viewModel(factory = PokemonListViewModel.Factory), // ViewModel para la lista de Pokémon
    favListViewModel: PokemonFavListViewModel = viewModel(factory = PokemonFavListViewModel.Factory) // ViewModel para manejar la lista de favoritos
) {

    // Obtener el estado de la UI desde el ViewModel de Pokémon
    val uiState = pokemonListVM.uiState.collectAsState().value
    // Obtener el estado de la UI desde el ViewModel de favoritos
    val favUiState by favListViewModel.uiState.collectAsState()

    // Buscar el Pokémon en la lista
    val pokemon = uiState.pokemon.find { it.name.equals(name, ignoreCase = true) }

    // Comprobar si el Pokémon ya está en la lista de favoritos
    val isAlreadyFavorite = pokemon?.let { poke ->
        favUiState.favorites.any { it.name == poke.name }
    } ?: false

    // Mensajes para mostrar en los Toasts
    val alreadyfavorite = stringResource(R.string.already)
    val favoriteadd = stringResource(R.string.addfavorite)
    // Obtener el contexto para mostrar los Toasts
    val context = LocalContext.current

    // Si la lista de Pokémon está vacía, mostrar pantalla de carga
    if (uiState.pokemon.isEmpty()) {
        LoadingDetail(name) // Mostrar la pantalla de carga
        return
    }

    // Si se encuentra el Pokémon, mostramos sus detalles
    if (pokemon != null) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background) // Fondo de la pantalla
        ) {
            item {
                // Tarjeta con los detalles del Pokémon en formato horizontal (más amplio)
                PokemonDetailCardLand(
                    pokemon = pokemon,
                    // Acción para añadir o quitar de favoritos
                    onFavoriteClick = {
                        if (isAlreadyFavorite) {
                            Toast.makeText(context, alreadyfavorite, Toast.LENGTH_SHORT).show() // Ya está en favoritos
                        } else {
                            val hexColor = String.format("#%08X", pokemon.colorFondo.toArgb()) // Convertir color a hexadecimal
                            val favoriteItem = PokeModel(
                                name = pokemon.name,
                                imageUrl = pokemon.imageUrl,
                                colorHex = hexColor,
                                types = pokemon.types,
                                abilities = pokemon.abilities,
                                stats = pokemon.stats
                            )
                            // Insertar en la lista de favoritos
                            favListViewModel.insertFavourite(favoriteItem)
                            Toast.makeText(context, favoriteadd, Toast.LENGTH_SHORT).show() // Mostrar mensaje de éxito
                        }
                    },
                    // Acción para volver a la pantalla anterior
                    onBackClick = { navController.navigateUp() }
                )
            }
        }
    } else {
        // Si no se encuentra el Pokémon, mostrar pantalla de "No encontrado"
        NotFoundDetail(name)
    }
}

