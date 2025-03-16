package com.example.pokemonhub.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.pokemonhub.datamodel.ListModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.pokemonhub.ui.components.PokemonDetailFavCard


@Composable
fun DetailFavPokemonScreen(
    name: String,
    favorite: ListModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val bgColor = try {
        Color(android.graphics.Color.parseColor(favorite.colorHex))
    } catch (e: Exception) {
        Color.Gray
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
    ) {
        item {
            PokemonDetailFavCard(
                onBackClick = { navController.navigateUp() },
                favorite = favorite,
            )
        }

    }
}