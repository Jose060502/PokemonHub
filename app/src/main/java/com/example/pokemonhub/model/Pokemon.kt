package com.example.pokemonhub.model

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelLazy

data class Pokemon(
    val name: String,
    val types: List<String>,
    val abilities : List<String>,
    val stats : List<String>,
    val imageUrl: String,
    val colorFondo : Color
)

