package com.example.pokemonhub

import android.app.Activity
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemonhub.model.Datasource
import com.example.pokemonhub.model.Datasource.pokemonFavList
import com.example.pokemonhub.ui.components.BottomNavigationBar
import com.example.pokemonhub.ui.screens.AboutScreenContent
import com.example.pokemonhub.ui.screens.DetailFavPokemonScreen
import com.example.pokemonhub.ui.screens.DetailPokemonScreenMedium
import com.example.pokemonhub.ui.screens.PokemonDetailsListCompactScreen
import com.example.pokemonhub.ui.screens.favouritelist.PokemonFavListViewModel
import com.example.pokemonhub.ui.screens.favouritelist.PokemonFavouriteListCompactScreen
import com.example.pokemonhub.ui.screens.favouritelist.PokemonFavouriteListMedExpScreen
import com.example.pokemonhub.ui.screens.pokemonlist.PokemonListCompactScreen
import com.example.pokemonhub.ui.screens.pokemonlist.PokemonListMedExpScreen
import com.example.pokemonhub.ui.screens.profilescreen.ProfileScreen
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
        val navController = rememberNavController()
        val favPokemons = remember { pokemonFavList }
        val windowSize = getWindowSizeClass(LocalContext.current as Activity)
        val currentRoute by navController.currentBackStackEntryFlow
            .map { it.destination.route }
            .collectAsState(initial = null)

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                if (currentRoute != null && currentRoute != "pokemon_detail/{pokemon_name}" && currentRoute != "pokemonfav_detail/{pokemon_name}") {
                    BottomNavigationBar(navController, currentRoute)
                }
            },
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "pokemon_list",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("pokemon_list") {
                    when (windowSize) {
                        WindowWidthSizeClass.Compact -> {
                            PokemonListCompactScreen(navController)
                        }
                        else -> {
                            PokemonListMedExpScreen(navController)
                        }
                    }
                }
                composable("fav_list") {
                    when (windowSize) {
                        WindowWidthSizeClass.Compact -> {
                            PokemonFavouriteListCompactScreen(navController)
                        }
                        else -> {
                            PokemonFavouriteListMedExpScreen(navController)
                        }
                    }
                }
                composable("profile") {
                    ProfileScreen(navController)
                }
                composable("aboutUs") {
                    when (windowSize) {
                        WindowWidthSizeClass.Compact -> {
                            AboutScreenContent()
                        }
                        else -> {
                            AboutScreenContent()
                        }
                    }
                }
                composable("pokemon_detail/{pokemon_name}") { backStackEntry ->
                    val pokemonName = backStackEntry.arguments?.getString("pokemon_name") ?: "NoName"
                    when (windowSize) {
                        WindowWidthSizeClass.Compact -> {
                            PokemonDetailsListCompactScreen(pokemonName, navController, Modifier.padding(8.dp))
                        }
                        else -> {
                            DetailPokemonScreenMedium(pokemonName, navController, Modifier.padding(8.dp))
                        }
                    }
                }
                composable("pokemonfav_detail/{pokemon_name}") {
                    val pokemonName = it.arguments?.getString("pokemon_name") ?: "NoName"
                    val favViewModel: PokemonFavListViewModel = viewModel(factory = PokemonFavListViewModel.Factory)
                    val favUiState by favViewModel.uiState.collectAsState()

                    val favorite = favUiState.favorites.find { it.name.equals(pokemonName, ignoreCase = true) }
                    when (windowSize) {
                        WindowWidthSizeClass.Compact -> {
                            if (favorite != null) {
                                DetailFavPokemonScreen(
                                    pokemonName, favorite, navController, Modifier.padding(8.dp),
                                )
                            }
                        }
                        else -> {
                            if (favorite != null) {
                                DetailFavPokemonScreen(pokemonName, favorite, navController, Modifier.padding(8.dp))
                            }
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PokemonAppPreview() {
        PokemonHubTheme {
            PokemonList()
        }
    }

//    @Composable
//    fun SplashScreen(onTimeout: () -> Unit) {
//        // Aquí defines el diseño de la pantalla de carga o splash
//        // Podría incluir un temporizador para salir de esta pantalla después de unos segundos
//        LaunchedEffect(Unit) {
//            kotlinx.coroutines.delay(2000) // Simula un tiempo de carga de 2 segundos
//            onTimeout()
//        }
//    }
}
