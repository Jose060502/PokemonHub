package com.example.pokemonhub.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokemonhub.R

@Composable
fun BottomNavigationBar(
    navController: NavController,
    currentRoute: String?
){
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.onTertiary
    ) {
        val items = listOf(
            BottomNavItem("pokemon_list", Icons.AutoMirrored.Filled.List, "Pokemon"),
            BottomNavItem("fav_list", Icons.Default.Favorite, "Favoritos"),
            BottomNavItem("profile", Icons.Default.Person, "Perfil"),
            BottomNavItem("aboutUs", Icons.TwoTone.Info, "Información"),
        )
        items.forEach{ item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun SearchBarCustom(
    textoIngresado: String,
    onQueryChange : (String) -> Unit,
) {
    OutlinedTextField(
        value = textoIngresado,
        onValueChange = onQueryChange,
        modifier =  Modifier.fillMaxWidth().padding(8.dp),
        placeholder = {Text(stringResource(R.string.search))},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search)
            )
        },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
        )
    )
}

data class BottomNavItem(val route: String, val icon: ImageVector, val label: String)