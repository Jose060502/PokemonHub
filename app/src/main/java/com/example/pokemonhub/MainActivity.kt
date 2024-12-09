package com.example.pokemonhub

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokemonhub.model.Datasource
import com.example.pokemonhub.model.Pokemon
import com.example.pokemonhub.ui.screens.AboutScreen
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
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonHubTheme {
                PokemonList()
                //PokemonDetailsList()
                //PokemonFavouriteList()
                //PokemonFavouriteDetailsList()
                //ProfileScreen()
            }
        }
    }

    @Composable
    fun PokemonList(){
        val pokemon = Datasource.getListXtimes(1)
        val windowsize = getWindowSizeClass(LocalContext.current as Activity)

        PokemonHubTheme {
            Scaffold (
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                when(windowsize){
                    WindowWidthSizeClass.Compact -> { PokemonListCompactScreen(pokemon, Modifier.padding(innerPadding)) }
                    else -> { PokemonListMedExpScreen(pokemon, Modifier.padding(innerPadding)) }
                }
            }
        }
    }
    @Composable
    fun PokemonDetailsList(){
        val pokemon = Datasource.getListXtimes(1)
        val windowsize = getWindowSizeClass(LocalContext.current as Activity)

        PokemonHubTheme {
            Scaffold (
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                when(windowsize){
                    WindowWidthSizeClass.Compact -> { PokemonDetailsListCompactScreen(pokemon, Modifier.padding(innerPadding)) }
                    else -> { PokemonDetailsListMedExpScreen(pokemon, Modifier.padding(innerPadding)) }
                }
            }
        }
    }
    @Composable
    fun PokemonFavouriteList(){
        val pokemon = Datasource.getListXtimes(1)
        val windowsize = getWindowSizeClass(LocalContext.current as Activity)

        PokemonHubTheme {
            Scaffold (
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                when(windowsize){
                    WindowWidthSizeClass.Compact -> { PokemonFavouriteListCompactScreen(Modifier.padding(innerPadding)) }
                    else -> { PokemonFavouriteListMedExpScreen(Modifier.padding(innerPadding)) }
                }
            }
        }
    }

    @Composable
    fun PokemonFavouriteDetailsList(){
        val pokemon = Datasource.getListXtimes(1)
        val windowsize = getWindowSizeClass(LocalContext.current as Activity)

        PokemonHubTheme {
            Scaffold (
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                when(windowsize){
                    WindowWidthSizeClass.Compact -> { PokemonFavouriteDetailsListCompactScreen(Modifier.padding(innerPadding)) }
                    else -> { PokemonFavouriteDetailsListMedExpScreen(Modifier.padding(innerPadding)) }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PookemonAppPreview() {
        PokemonHubTheme  {
            PokemonList()
        }
    }

    @Composable
    fun MainScreen() {
        // Controla si la SplashScreen se muestra o no
        var showSplashScreen by remember { mutableStateOf(true) }

        val context = LocalContext.current

        if (showSplashScreen) {
            SplashScreen { showSplashScreen = false }
        } else {
            // Lanza AboutScreen usando un Intent
            LaunchedEffect(Unit) {
                val intent = Intent(context, AboutScreen::class.java)
                context.startActivity(intent)
            }
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
