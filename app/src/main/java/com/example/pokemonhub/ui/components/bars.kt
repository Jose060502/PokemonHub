package com.example.pokemonhub.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(
    navController: NavController,
    currentRoute: String?
){
    NavigationBar {
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

data class BottomNavItem(val route: String, val icon: ImageVector, val label: String)