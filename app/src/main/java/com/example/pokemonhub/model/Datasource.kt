/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.pokemonhub.model
import androidx.compose.ui.graphics.Color
import com.example.pokemonhub.R


object Datasource {

    val pokemonList: () -> MutableList<Pokemon> = {
        mutableListOf(
            Pokemon(
                name = "Bulbasaur",
                types = listOf("Planta", "Veneno"),
                abilities = listOf("Espesura", "Clorofila"),
                stats = listOf("PS: 45", "Ataque: 49", "Defensa: 49", "At. Esp.: 65", "Def. Esp.: 65", "Velocidad: 45"),
                imageUrl = "Bulbasaur",
                colorFondo = Color.LightGray
            ),
            Pokemon(
                name = "Ivysaur",
                types = listOf("Planta", "Veneno"),
                abilities = listOf("Espesura", "Clorofila"),
                stats = listOf("PS: 60", "Ataque: 62", "Defensa: 63", "At. Esp.: 80", "Def. Esp.: 80", "Velocidad: 60"),
                imageUrl = "Ivysaur",
                colorFondo = Color.LightGray
            ),
            Pokemon(
                name = "Venusaur",
                types = listOf("Planta", "Veneno"),
                abilities = listOf("Espesura", "Clorofila"),
                stats = listOf("PS: 80", "Ataque: 82", "Defensa: 83", "At. Esp.: 100", "Def. Esp.: 100", "Velocidad: 80"),
                imageUrl = "Venusaur",
                colorFondo = Color.LightGray
            ),
            Pokemon(
                name = "Charmander",
                types = listOf("Fuego"),
                abilities = listOf("Mar Llamas", "Poder Solar"),
                stats = listOf("PS: 39", "Ataque: 52", "Defensa: 43", "At. Esp.: 60", "Def. Esp.: 50", "Velocidad: 65"),
                imageUrl = "Charmander",
                colorFondo = Color.LightGray
            ),
            Pokemon(
                name = "Charmeleon",
                types = listOf("Fuego"),
                abilities = listOf("Mar Llamas", "Poder Solar"),
                stats = listOf("PS: 58", "Ataque: 64", "Defensa: 58", "At. Esp.: 80", "Def. Esp.: 65", "Velocidad: 80"),
                imageUrl = "Charmeleon",
                colorFondo = Color.LightGray
            ),
            Pokemon(
                name = "Charizard",
                types = listOf("Fuego", "Volador"),
                abilities = listOf("Mar Llamas", "Poder Solar"),
                stats = listOf("PS: 78", "Ataque: 84", "Defensa: 78", "At. Esp.: 109", "Def. Esp.: 85", "Velocidad: 100"),
                imageUrl = "Charizard",
                colorFondo = Color.LightGray
            ),
            Pokemon(
                name = "Squirtle",
                types = listOf("Agua"),
                abilities = listOf("Torrente", "Cura Lluvia"),
                stats = listOf("PS: 44", "Ataque: 48", "Defensa: 65", "At. Esp.: 50", "Def. Esp.: 64", "Velocidad: 43"),
                imageUrl = "Squirtle",
                colorFondo = Color.LightGray
            ),
            Pokemon(
                name = "Wartortle",
                types = listOf("Agua"),
                abilities = listOf("Torrente", "Cura Lluvia"),
                stats = listOf("PS: 59", "Ataque: 63", "Defensa: 80", "At. Esp.: 65", "Def. Esp.: 80", "Velocidad: 58"),
                imageUrl = "Wartortle",
                colorFondo = Color.LightGray
            ),
            Pokemon(
                name = "Blastoise",
                types = listOf("Agua"),
                abilities = listOf("Torrente", "Cura Lluvia"),
                stats = listOf("PS: 79", "Ataque: 83", "Defensa: 100", "At. Esp.: 85", "Def. Esp.: 105", "Velocidad: 78"),
                imageUrl = "Blastoise",
                colorFondo = Color.LightGray
            ),
            Pokemon(
                name = "Mewtwo",
                types = listOf("Psíquico"),
                abilities = listOf("Presión", "Nerviosismo"),
                stats = listOf("PS: 106", "Ataque: 110", "Defensa: 90", "At. Esp.: 154", "Def. Esp.: 90", "Velocidad: 130"),
                imageUrl = "Mewtwo",
                colorFondo = Color.LightGray
            ),
            Pokemon(
                name = "Mew",
                types = listOf("Psíquico"),
                abilities = listOf("Sincronía"),
                stats = listOf("PS: 100", "Ataque: 100", "Defensa: 100", "At. Esp.: 100", "Def. Esp.: 100", "Velocidad: 100"),
                imageUrl = "Mew",
                colorFondo = Color.LightGray
            )
        )
    }

    val pokemonFavList: () -> MutableList<Pokemon> = {
        mutableListOf(
            Pokemon(
                name = "Bulbasaur",
                types = listOf("Planta", "Veneno"),
                abilities = listOf("Espesura", "Clorofila"),
                stats = listOf(
                    "PS: 45",
                    "Ataque: 49",
                    "Defensa: 49",
                    "At. Esp.: 65",
                    "Def. Esp.: 65",
                    "Velocidad: 45"
                ),
                imageUrl = "Bulbasaur",
                colorFondo = Color.LightGray
            ),
            Pokemon(
                name = "Ivysaur",
                types = listOf("Planta", "Veneno"),
                abilities = listOf("Espesura", "Clorofila"),
                stats = listOf(
                    "PS: 60",
                    "Ataque: 62",
                    "Defensa: 63",
                    "At. Esp.: 80",
                    "Def. Esp.: 80",
                    "Velocidad: 60"
                ),
                imageUrl = "Ivysaur",
                colorFondo = Color.LightGray
            ),
            Pokemon(
                name = "Venusaur",
                types = listOf("Planta", "Veneno"),
                abilities = listOf("Espesura", "Clorofila"),
                stats = listOf(
                    "PS: 80",
                    "Ataque: 82",
                    "Defensa: 83",
                    "At. Esp.: 100",
                    "Def. Esp.: 100",
                    "Velocidad: 80"
                ),
                imageUrl = "Venusaur",
                colorFondo = Color.LightGray
            ),
            Pokemon(
                name = "Charmander",
                types = listOf("Fuego"),
                abilities = listOf("Mar Llamas", "Poder Solar"),
                stats = listOf(
                    "PS: 39",
                    "Ataque: 52",
                    "Defensa: 43",
                    "At. Esp.: 60",
                    "Def. Esp.: 50",
                    "Velocidad: 65"
                ),
                imageUrl = "Charmander",
                colorFondo = Color.LightGray
            ),
            Pokemon(
                name = "Charmeleon",
                types = listOf("Fuego"),
                abilities = listOf("Mar Llamas", "Poder Solar"),
                stats = listOf(
                    "PS: 58",
                    "Ataque: 64",
                    "Defensa: 58",
                    "At. Esp.: 80",
                    "Def. Esp.: 65",
                    "Velocidad: 80"
                ),
                imageUrl = "Charmeleon",
                colorFondo = Color.LightGray
            ),
            Pokemon(
                name = "Charizard",
                types = listOf("Fuego", "Volador"),
                abilities = listOf("Mar Llamas", "Poder Solar"),
                stats = listOf(
                    "PS: 78",
                    "Ataque: 84",
                    "Defensa: 78",
                    "At. Esp.: 109",
                    "Def. Esp.: 85",
                    "Velocidad: 100"
                ),
                imageUrl = "Charizard",
                colorFondo = Color.LightGray
            ),
            Pokemon(
                name = "Squirtle",
                types = listOf("Agua"),
                abilities = listOf("Torrente", "Cura Lluvia"),
                stats = listOf(
                    "PS: 44",
                    "Ataque: 48",
                    "Defensa: 65",
                    "At. Esp.: 50",
                    "Def. Esp.: 64",
                    "Velocidad: 43"
                ),
                imageUrl = "Squirtle",
                colorFondo = Color.LightGray
            ),
            Pokemon(
                name = "Wartortle",
                types = listOf("Agua"),
                abilities = listOf("Torrente", "Cura Lluvia"),
                stats = listOf(
                    "PS: 59",
                    "Ataque: 63",
                    "Defensa: 80",
                    "At. Esp.: 65",
                    "Def. Esp.: 80",
                    "Velocidad: 58"
                ),
                imageUrl = "Wartortle",
                colorFondo = Color.LightGray
            ),
            Pokemon(
                name = "Blastoise",
                types = listOf("Agua"),
                abilities = listOf("Torrente", "Cura Lluvia"),
                stats = listOf(
                    "PS: 79",
                    "Ataque: 83",
                    "Defensa: 100",
                    "At. Esp.: 85",
                    "Def. Esp.: 105",
                    "Velocidad: 78"
                ),
                imageUrl = "Blastoise",
                colorFondo = Color.LightGray
            ),
        )
    }





//    val getListXtimes: (Int) -> MutableList<Pokemon> = { times ->
//        val list = mutableListOf<Pokemon>()
//        for (i in 1..times) {
//            list.addAll(pokemonList())
//        }
//        list
//    }
//
//    val getPokemonByName: (String) -> Pokemon? = { name ->
//        pokemonList().find { it.name == name } ?: null
//    }
//
//    val getSomeRandPokemon: (Int) -> MutableList<Pokemon> = { num ->
//        val pokemon = pokemonList() // Obtiene la lista completa
//        if (num <= pokemon.size) {
//            pokemon.shuffled().take(num).toMutableList() // Baraja y toma `num` elementos
//        } else {
//            pokemon.shuffled().toMutableList() // Baraja y devuelve todos los elementos
//        }
//    }



    fun getDrawableIdByName(name: String): Int {
        return when (name) {
            "Bulbasaur" -> R.drawable.bulbasaur
            "BulbasaurShiny" -> R.drawable.bulbasaurshiny
            "Ivysaur" -> R.drawable.ivysaur
            "IvysaurShiny" -> R.drawable.ivysaurshiny
            "Venusaur" -> R.drawable.venusaur
            "VenusaurShiny" -> R.drawable.venusaurshiny
            "Charmander" -> R.drawable.charmander
            "CharmanderShiny" -> R.drawable.charmandershiny
            "Charmeleon" -> R.drawable.charmeleon
            "CharmeleonShiny" -> R.drawable.charmeleonshiny
            "Charizard" -> R.drawable.charizard
            "CharizardShiny" -> R.drawable.charizardshiny
            "Squirtle" -> R.drawable.squirtle
            "SquirtleShiny" -> R.drawable.squirtleshiny
            "Wartortle" -> R.drawable.wartortle
            "WartortleShiny" -> R.drawable.wartortleshiny
            "Blastoise" -> R.drawable.blastoise
            "BlastoiseShiny" -> R.drawable.blastoiseshiny
            "Articuno" -> R.drawable.articuno
            "ArticunoShiny" -> R.drawable.articunoshiny
            "Zapdos" -> R.drawable.zapdos
            "ZapdosShiny" -> R.drawable.zapdosshiny
            "Moltres" -> R.drawable.moltres
            "MoltresShiny" -> R.drawable.moltresshiny
            "Mewtwo" -> R.drawable.mewtwo
            "MewtwoShiny" -> R.drawable.mewtwoshiny
            "Mew" -> R.drawable.mew
            "MewShiny" -> R.drawable.mew
            else -> R.drawable.pokemonhub
        }
    }
}