package com.example.pokemonhub.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokemonhub.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreenContent() {
    val context = LocalContext.current // Contexto obtenido aquí

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
                        " También podrás revisar en qué juegos aparece el Pokémon que has buscado.",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
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
            Button(onClick = { sendEmail(context) }) {
                Text(text = "E-mail")
            }
        }
    }
}

// Función para enviar un correo electrónico usando un Intent
fun sendEmail(context: Context) {
    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:") // Solo aplicaciones de email
        putExtra(Intent.EXTRA_EMAIL, arrayOf("destinatario@example.com"))
        putExtra(Intent.EXTRA_SUBJECT, "Asunto del correo")
        putExtra(Intent.EXTRA_TEXT, "Cuerpo del mensaje")
    }

    try {
        context.startActivity(Intent.createChooser(emailIntent, "Enviar email usando:"))
    } catch (ex: android.content.ActivityNotFoundException) {
        Toast.makeText(context, "No hay aplicaciones de correo instaladas.", Toast.LENGTH_SHORT).show()
    }
}


