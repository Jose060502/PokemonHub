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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

        if (showSplashScreen) {
            SplashScreen { showSplashScreen = false }
        } else {
            AboutScreen()
        }
    }

    // SplashScreen que solo muestra el logo
    @Composable
    fun SplashScreen(onTimeout: () -> Unit) {
        LaunchedEffect(Unit) {
            onTimeout() // Pasar a la siguiente pantalla
        }
    }

    // Pantalla "Sobre la Aplicación" con TopBar y el logo en la información
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AboutScreen() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Text("PokeInfo")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Información sobre Pokémon",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Esta aplicación permite buscar y consultar " +
                            "información detallada sobre cada Pokémon. " +
                            "Podrás conocer sus habilidades, tipos, estadísticas, y explorar su evolución." +
                            " Y también se podra revisar en los diferentes juegos que aparecen el pokemon" +
                            " que has buscado.",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Logo colocado aquí, entre el texto y el botón de contacto
                Image(
                    painter = painterResource(id = R.drawable.pokemonhub),
                    contentDescription = "Logo de la App",
                    modifier = Modifier.size(300.dp) // Ajusta el tamaño del logo en la información
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Versión: 1.0",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(24.dp))

                // Botón para contactar
                Button(onClick = { sendEmail() }) {
                    Text(text = "E-mail")
                }
            }
        }
    }

    // Función para enviar un correo electrónico usando un Intent
    private fun sendEmail() {
        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("contacto@app.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Información sobre la Aplicación Pokémon")
            putExtra(Intent.EXTRA_TEXT, "Esta app permitirá consultar información sobre Pokémon, incluyendo estadísticas, tipos, habilidades, y más.")
        }
        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email usando:"))
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(this, "No hay aplicaciones de correo instaladas.", Toast.LENGTH_SHORT).show()
        }
    }
}
