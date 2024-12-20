package com.example.pokemonhub

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemonhub.model.Datasource
import com.example.pokemonhub.ui.components.BottomNavigationBar
import com.example.pokemonhub.ui.screens.AboutScreenContent
import com.example.pokemonhub.ui.screens.PokemonDetailsListCompactScreen
import com.example.pokemonhub.ui.screens.PokemonDetailsListMedExpScreen
import com.example.pokemonhub.ui.screens.PokemonFavouriteDetailsListCompactScreen
import com.example.pokemonhub.ui.screens.PokemonFavouriteDetailsListMedExpScreen
import com.example.pokemonhub.ui.screens.PokemonFavouriteListCompactScreen
import com.example.pokemonhub.ui.screens.PokemonFavouriteListMedExpScreen
import com.example.pokemonhub.ui.screens.PokemonListCompactScreen
import com.example.pokemonhub.ui.screens.PokemonListMedExpScreen
import com.example.pokemonhub.ui.screens.ProfileScreen
import com.example.pokemonhub.ui.theme.PokemonHubTheme
import com.example.pokemonhub.utils.getWindowSizeClass
import kotlinx.coroutines.flow.map

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonHubTheme {
                PokemonList()

            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PokemonList() {
        val pokemon = Datasource.getListXtimes(1)
        val windowsize = getWindowSizeClass(LocalContext.current as Activity)
        val navController = rememberNavController()
        val currentRoute by navController.currentBackStackEntryFlow.map { it.destination.route }
            .collectAsState(initial = null)

        var searchQuery by remember { mutableStateOf("") }
        val filteredPokemon = pokemon.filter {
            it.name.contains(searchQuery, ignoreCase = true)
        }

        PokemonHubTheme {
            Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                if (currentRoute?.contains("pokemon_detail") == false && currentRoute?.contains(
                        "pokemon_fav_details"
                    ) == false && currentRoute?.contains("profile") == false && currentRoute?.contains(
                        "aboutUs"
                    ) == false && currentRoute?.contains("fav_list") == false
                ) {
                    TopAppBar(title = {
                        Text("Pokemon List")
                    }, actions = {
                        // Barra de búsqueda
                        TextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            placeholder = { Text("Buscar...") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            singleLine = true
                        )
                    })
                }
            }, bottomBar = {
                if (windowsize == WindowWidthSizeClass.Compact && currentRoute?.contains("pokemon_detail") == false && currentRoute?.contains(
                        "pokemon_fav_details"
                    ) == false
                ) BottomNavigationBar(navController, currentRoute)
            }, floatingActionButton = { }) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "pokemon_list",
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable("pokemon_list") {
                        when (windowsize) {
                            WindowWidthSizeClass.Compact -> {
                                PokemonListCompactScreen(
                                    filteredPokemon,
                                    navController,
                                    Modifier.padding(horizontal = 8.dp)
                                )
                            }

                            else -> {
                                PokemonListMedExpScreen(
                                    filteredPokemon, Modifier.padding(horizontal = 8.dp)
                                )
                            }
                        }
                    }
                    composable("fav_list") {
                        val favList = Datasource.getSomeRandPokemon(6)
                        when (windowsize) {
                            WindowWidthSizeClass.Compact -> {
                                PokemonFavouriteListCompactScreen(
                                    favList, Modifier.padding(horizontal = 8.dp)
                                )
                            }

                            else -> {
                                PokemonFavouriteListMedExpScreen(favList, Modifier.padding(8.dp))
                            }
                        }
                    }
                    composable("profile") {
                        ProfileScreen(Modifier.padding(horizontal = 8.dp))
                    }
                    composable("pokemon_detail/{pokemon_name}") {
                        val pokemonNamePokedex = it.arguments?.getString("pokemon_name") ?: "NoName"
                        when (windowsize) {
                            WindowWidthSizeClass.Compact -> {
                                PokemonDetailsListCompactScreen(
                                    pokemonNamePokedex, navController, Modifier.padding(8.dp)
                                )
                            }

                            else -> {
                                PokemonDetailsListMedExpScreen(
                                    pokemonNamePokedex, navController, Modifier.padding(8.dp)
                                )
                            }
                        }
                    }
                    composable("pokemon_fav_details/{pokemon_name}") {
                        val pokemonNamePokedex = it.arguments?.getString("pokemon_name") ?: "NoName"
                        when (windowsize) {
                            WindowWidthSizeClass.Compact -> {
                                PokemonFavouriteDetailsListCompactScreen(
                                    pokemonNamePokedex, navController, Modifier.padding(8.dp)
                                )
                            }

                            else -> {
                                PokemonFavouriteDetailsListMedExpScreen(
                                    pokemonNamePokedex, navController, Modifier.padding(8.dp)
                                )
                            }
                        }
                    }
                    composable("aboutUs") {
                        AboutScreenContent()
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PookemonAppPreview() {
        PokemonHubTheme {
            PokemonList()
        }
    }

    @Composable
    fun SplashScreen(onTimeout: () -> Unit) {
        // Aquí defines el diseño de la pantalla de carga o splash
        // Podría incluir un temporizador para salir de esta pantalla después de unos segundos
        LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(2000) // Simula un tiempo de carga de 2 segundos
            onTimeout()
        }
    }
}
