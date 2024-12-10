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
import androidx.compose.ui.draw.clip
import com.example.pokemonhub.R


@Composable
fun ProfileScreen(modifier: Modifier = Modifier){
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
           painter = painterResource(id = R.drawable.fotoperfil),// Reemplaza con tu imagen
            contentDescription = "Foto de perfil",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape) // Forma circular para la imagen
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape), // Borde circular
            contentScale = ContentScale.Crop // Asegura que la imagen esté recortada en círculo
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nombre del usuario (puedes agregar un campo para el nombre si lo deseas)
        Text(
            text = "Jose060502", // Aquí puedes poner el nombre del usuario
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

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
            Spacer(modifier = Modifier.width(8.dp)) // Espacio entre el ícono y el texto
            Text(
                text = userPhone,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Correo electrónico con ícono
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
            Spacer(modifier = Modifier.width(8.dp)) // Espacio entre el ícono y el texto
            Text(
                text = userEmail,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

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

            // Botón Cerrar sesión
            Button(
                onClick = { /* Acción de cerrar sesión aquí */ },
                modifier = Modifier.weight(1f)
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}

