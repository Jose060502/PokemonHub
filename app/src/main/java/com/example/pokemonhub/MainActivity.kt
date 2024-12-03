package com.example.pokemonhub

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokemonhub.ui.screens.AboutScreen
import com.example.pokemonhub.ui.theme.PokemonHubTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonHubTheme {
                MainScreen()
            }
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
