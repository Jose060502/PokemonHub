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
    navController: NavController,
    profileViewModel: ProfileViewModel = viewModel(factory = ProfileViewModel.Factory)
){
    val profileUiState by profileViewModel.uiState.collectAsState()
    val isLogged = profileUiState.nombreUsuario.isNotBlank()
    var tempUserName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(top = 40.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MedHeaderComp(stringResource(id = R.string.userinfo))
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(id = R.drawable.fotoperfil),
            contentDescription = stringResource(id = R.string.imagen_perfil),
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )

        if (isLogged) {
            StandardText(stringResource(R.string.username,profileUiState.nombreUsuario))
        } else {
            StandardText(stringResource(R.string.insert_username))
            TextField(
                value = tempUserName,
                onValueChange = { tempUserName = it },
                label = { Text(text = stringResource(id = R.string.username)) },
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        StandardText(stringResource(R.string.select))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            RadioButton(
                selected = profileUiState.modoVisualizacion == ModoVisualizacion.CLARO,
                onClick = {
                    val nameToUse = if (isLogged) profileUiState.nombreUsuario else tempUserName
                    profileViewModel.setSettings(
                        nombreUsuario = nameToUse,
                        modoVisualizacion = ModoVisualizacion.CLARO
                    )
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = colorResource(id = R.color.white),
                    unselectedColor = colorResource(id = R.color.white),
                )
            )
            Text(text = stringResource(R.string.light), modifier = Modifier.clickable {
                val nameToUse = if (isLogged) profileUiState.nombreUsuario else tempUserName
                profileViewModel.setSettings(
                    nombreUsuario = nameToUse,
                    modoVisualizacion = ModoVisualizacion.CLARO
                )
            },
                color = MaterialTheme.colorScheme.surface
            )
            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = profileUiState.modoVisualizacion == ModoVisualizacion.OSCURO,
                onClick = {
                    val nameToUse = if (isLogged) profileUiState.nombreUsuario else tempUserName
                    profileViewModel.setSettings(
                        nombreUsuario = nameToUse,
                        modoVisualizacion = ModoVisualizacion.OSCURO
                    )
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = colorResource(id = R.color.white),
                    unselectedColor = colorResource(id = R.color.white),
                )
            )
            Text(text = stringResource(R.string.dark), modifier = Modifier.clickable {
                val nameToUse = if (isLogged) profileUiState.nombreUsuario else tempUserName
                profileViewModel.setSettings(
                    nombreUsuario = nameToUse,
                    modoVisualizacion = ModoVisualizacion.OSCURO
                )
            },
                color = MaterialTheme.colorScheme.surface)
            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = profileUiState.modoVisualizacion == ModoVisualizacion.SYSTEM,
                onClick = {
                    val nameToUse = if (isLogged) profileUiState.nombreUsuario else tempUserName
                    profileViewModel.setSettings(
                        nombreUsuario = nameToUse,
                        modoVisualizacion = ModoVisualizacion.SYSTEM
                    )
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = colorResource(id = R.color.white),
                    unselectedColor = colorResource(id = R.color.white),
                )
            )
            Text(text = stringResource(R.string.system,MaterialTheme.colorScheme.onPrimary), modifier = Modifier.clickable {
                val nameToUse = if (isLogged) profileUiState.nombreUsuario else tempUserName
                profileViewModel.setSettings(
                    nombreUsuario = nameToUse,
                    modoVisualizacion = ModoVisualizacion.SYSTEM
                )
            },
                color = MaterialTheme.colorScheme.surface
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        StandardButtonColor(
            if (isLogged) stringResource(id = R.string.logout) else stringResource(id = R.string.login),
            modifier = Modifier.padding(8.dp),
            onClick = {
                if (isLogged) {
                    profileViewModel.setSettings(
                        nombreUsuario = "",
                        modoVisualizacion = profileUiState.modoVisualizacion
                    )
                    tempUserName = ""
                } else {
                    profileViewModel.setSettings(
                        nombreUsuario = tempUserName,
                        modoVisualizacion = profileUiState.modoVisualizacion
                    )
                }
            }
        )
    }
}



