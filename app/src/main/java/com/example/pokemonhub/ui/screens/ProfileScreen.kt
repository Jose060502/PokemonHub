package com.example.pokemonhub.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Call
import androidx.compose.material.icons.twotone.Email
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import com.example.pokemonhub.R

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    // Estado que determina si el usuario está logueado o no
    var isLoggedIn by remember { mutableStateOf(false) }

    // Función que cambia el estado de login/logout
    val toggleLoginLogout = {
        isLoggedIn = !isLoggedIn
    }

    val userPhone = "+34 633 076 804"
    val userEmail = "jsedgon135@g.educaand.es"

    // Pantalla de perfil
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Foto de perfil
        Image(
            painter = painterResource(id = R.drawable.fotoperfil), // Reemplaza con tu imagen
            contentDescription = "Foto de perfil",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape) // Forma circular para la imagen
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape), // Borde circular
            contentScale = ContentScale.Crop // Asegura que la imagen esté recortada en círculo
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nombre del usuario
        Text(
            text = "Jose060502", // Aquí puedes poner el nombre del usuario
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Teléfono del usuario
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.TwoTone.Call,
                contentDescription = "Teléfono",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = userPhone,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Correo electrónico del usuario
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.TwoTone.Email,
                contentDescription = "Correo electrónico",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = userEmail,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Información de login/logout
        Text(
            text = if (isLoggedIn) "Bienvenido, Usuario!" else "No has iniciado sesión",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Botones
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Botón Editar perfil
            Button(
                onClick = { /* Acción de editar perfil aquí */ },
                modifier = Modifier.weight(1f)
            ) {
                Text("Editar perfil")
            }

            // Botón Login/Logout
            Button(
                onClick = toggleLoginLogout,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = if (isLoggedIn) "Cerrar sesión" else "Iniciar sesión")
            }
        }
    }
}



