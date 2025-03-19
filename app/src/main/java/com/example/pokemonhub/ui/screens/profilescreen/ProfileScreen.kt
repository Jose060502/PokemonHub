package com.example.pokemonhub.ui.screens.profilescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Call
import androidx.compose.material.icons.twotone.Email
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pokemonhub.R
import com.example.pokemonhub.data.ModoVisualizacion
import com.example.pokemonhub.ui.components.MedHeaderComp
import com.example.pokemonhub.ui.components.StandardButtonColor
import com.example.pokemonhub.ui.components.StandardText

@Composable
fun ProfileScreen(
    navController: NavController, // El controlador de navegación para gestionar las rutas de la app
    profileViewModel: ProfileViewModel = viewModel(factory = ProfileViewModel.Factory) // ViewModel de la pantalla, usando la factory para su creación
) {
    // Se obtiene el estado de la UI a través del ViewModel
    val profileUiState by profileViewModel.uiState.collectAsState()
    val isLogged = profileUiState.nombreUsuario.isNotBlank() // Se verifica si el usuario está logueado basándonos en si hay un nombre de usuario

    var tempUserName by remember { mutableStateOf("") } // Variable para guardar temporalmente el nombre de usuario si no está logueado

    // Layout principal utilizando un Column para organizar los elementos verticalmente
    Column(
        modifier = Modifier
            .fillMaxSize() // La columna ocupa todo el tamaño disponible
            .background(MaterialTheme.colorScheme.primary) // Fondo con el color primario del tema
            .padding(top = 40.dp), // Espaciado superior de 40dp
        verticalArrangement = Arrangement.Top, // Alineación vertical en la parte superior
        horizontalAlignment = Alignment.CenterHorizontally // Alineación horizontal en el centro
    ) {
        // Componente de encabezado (puede ser un título o similar)
        MedHeaderComp(stringResource(id = R.string.userinfo))
        Spacer(modifier = Modifier.height(40.dp)) // Espaciador de 40dp entre elementos

        // Imagen de perfil, con un círculo recortado para la forma de la foto
        Image(
            painter = painterResource(id = R.drawable.fotoperfil),
            contentDescription = stringResource(id = R.string.imagen_perfil),
            modifier = Modifier
                .size(200.dp) // Tamaño de la imagen
                .clip(CircleShape) // Forma circular
        )

        // Si el usuario está logueado, muestra su nombre de usuario
        if (isLogged) {
            StandardText(stringResource(R.string.username, profileUiState.nombreUsuario))
        } else {
            // Si no está logueado, se solicita el nombre de usuario
            StandardText(stringResource(R.string.insert_username))
            TextField(
                value = tempUserName, // El valor del TextField es la variable temporal
                onValueChange = { tempUserName = it }, // Al cambiar el texto, se actualiza la variable
                label = { Text(text = stringResource(id = R.string.username)) }, // Etiqueta del campo
                modifier = Modifier.padding(8.dp) // Espaciado alrededor del campo
            )
        }

        Spacer(modifier = Modifier.height(16.dp)) // Espaciador de 16dp

        // Texto indicando que se debe elegir un modo de visualización
        StandardText(stringResource(R.string.select))

        // Sección de selección del modo de visualización (Claro, Oscuro, Sistema)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            // RadioButton para el modo Claro
            RadioButton(
                selected = profileUiState.modoVisualizacion == ModoVisualizacion.CLARO, // Se selecciona si el modo actual es Claro
                onClick = {
                    // Al hacer clic, se actualiza el estado en el ViewModel con el modo seleccionado
                    val nameToUse = if (isLogged) profileUiState.nombreUsuario else tempUserName
                    profileViewModel.setSettings(
                        nombreUsuario = nameToUse,
                        modoVisualizacion = ModoVisualizacion.CLARO
                    )
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = colorResource(id = R.color.white),
                    unselectedColor = colorResource(id = R.color.white)
                )
            )
            // Texto junto al RadioButton
            Text(text = stringResource(R.string.light), modifier = Modifier.clickable {
                // Actualiza el modo visual cuando se hace clic
                val nameToUse = if (isLogged) profileUiState.nombreUsuario else tempUserName
                profileViewModel.setSettings(
                    nombreUsuario = nameToUse,
                    modoVisualizacion = ModoVisualizacion.CLARO
                )
            }, color = MaterialTheme.colorScheme.surface)

            Spacer(modifier = Modifier.width(16.dp)) // Espaciador de 16dp

            // RadioButton para el modo Oscuro
            RadioButton(
                selected = profileUiState.modoVisualizacion == ModoVisualizacion.OSCURO, // Se selecciona si el modo actual es Oscuro
                onClick = {
                    val nameToUse = if (isLogged) profileUiState.nombreUsuario else tempUserName
                    profileViewModel.setSettings(
                        nombreUsuario = nameToUse,
                        modoVisualizacion = ModoVisualizacion.OSCURO
                    )
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = colorResource(id = R.color.white),
                    unselectedColor = colorResource(id = R.color.white)
                )
            )
            // Texto junto al RadioButton
            Text(text = stringResource(R.string.dark), modifier = Modifier.clickable {
                val nameToUse = if (isLogged) profileUiState.nombreUsuario else tempUserName
                profileViewModel.setSettings(
                    nombreUsuario = nameToUse,
                    modoVisualizacion = ModoVisualizacion.OSCURO
                )
            }, color = MaterialTheme.colorScheme.surface)

            Spacer(modifier = Modifier.width(16.dp)) // Espaciador de 16dp

            // RadioButton para el modo Sistema (ajuste según el sistema operativo)
            RadioButton(
                selected = profileUiState.modoVisualizacion == ModoVisualizacion.SYSTEM, // Se selecciona si el modo actual es Sistema
                onClick = {
                    val nameToUse = if (isLogged) profileUiState.nombreUsuario else tempUserName
                    profileViewModel.setSettings(
                        nombreUsuario = nameToUse,
                        modoVisualizacion = ModoVisualizacion.SYSTEM
                    )
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = colorResource(id = R.color.white),
                    unselectedColor = colorResource(id = R.color.white)
                )
            )
            // Texto junto al RadioButton
            Text(text = stringResource(R.string.system, MaterialTheme.colorScheme.onPrimary), modifier = Modifier.clickable {
                val nameToUse = if (isLogged) profileUiState.nombreUsuario else tempUserName
                profileViewModel.setSettings(
                    nombreUsuario = nameToUse,
                    modoVisualizacion = ModoVisualizacion.SYSTEM
                )
            }, color = MaterialTheme.colorScheme.surface)
        }

        Spacer(modifier = Modifier.height(10.dp)) // Espaciador de 10dp

        // Botón para iniciar o cerrar sesión dependiendo de si el usuario está logueado
        StandardButtonColor(
            if (isLogged) stringResource(id = R.string.logout, MaterialTheme.colorScheme.onBackground)
            else stringResource(id = R.string.login, MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.padding(8.dp),
            onClick = {
                if (isLogged) {
                    // Si el usuario está logueado, cierra sesión y limpia el nombre
                    profileViewModel.setSettings(
                        nombreUsuario = "",
                        modoVisualizacion = profileUiState.modoVisualizacion
                    )
                    tempUserName = "" // Restablece el nombre de usuario temporal
                } else {
                    // Si no está logueado, guarda el nombre de usuario ingresado
                    profileViewModel.setSettings(
                        nombreUsuario = tempUserName,
                        modoVisualizacion = profileUiState.modoVisualizacion
                    )
                }
            }
        )
    }
}




