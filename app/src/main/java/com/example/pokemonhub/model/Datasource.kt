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

import com.example.pokemonhub.R


object Datasource {

    val heroList: () -> MutableList<Pokemon> = {
        mutableListOf<Pokemon>(
            Pokemon(
                "Bulbasaur",
                "Planta-Veneno",
                1,
                "Espesura",
                "Clorofila",
                45,
                49,
                49,
                65,
                65,
                45,
                "Bulbasaur",
                "Una rara semilla fue plantada en su espalda al nacer. La planta brota y crece con este Pokémon."
            ),
            Pokemon(
                "Ivysaur",
                "Planta-Veneno",
                2,
                "Espesura",
                "Clorofila",
                60,
                62,
                63,
                80,
                80,
                60,
                "Ivysaur",
                "Cuando el bulbo de su espalda crece, parece no poder ponerse de pie sobre sus patas traseras."
            ),
            Pokemon(
                "Venusaur",
                "Planta-Veneno",
                3,
                "Espesura",
                "Clorofila",
                80,
                82,
                83,
                100,
                100,
                80,
                "Venusaur",
                "La planta florece cuando absorbe energía solar. Ésta le obliga a ponerse en busca de la luz solar."
            ),
            Pokemon(
                "Charmander",
                "Fuego",
                4,
                "Mar llamas",
                "Poder solar",
                39,
                52,
                43,
                60,
                50,
                65,
                "Charmander",
                "Prefiere los sitios calientes. Dicen que cuando llueve sale vapor de la punta de su cola."
            ),
        ).apply { shuffle() }
    }

    val getListXtimes: (Int) -> MutableList<Pokemon> = { times ->
        val list = mutableListOf<Pokemon>()
        for (i in 1..times) {
            list.addAll(heroList())
        }
        list.shuffle()
        list
    }

    val getHeroByName: (String) -> Pokemon? = { name ->
        heroList().find { it.name == name } ?: null
    }

    val getSomeRandHeroes: (Int) -> MutableList<Pokemon> = { num ->
        val heroes = heroList()
        if (num <= heroes.size) heroes.subList(0, num)
        heroes
    }

    fun getDrawableIdByName(name: String): Int {
        return when (name) {
            "Bulbasaur" -> R.drawable.bulbasaur
            "Ivysaur" -> R.drawable.ivysaur
            "Venusaur" -> R.drawable.venusaur
            "Charmander" -> R.drawable.charmander
            "Charmeleon" -> R.drawable.charmeleon
            "Charizard" -> R.drawable.charizard
            "Squirtle" -> R.drawable.squirtle
            "Wartortle" -> R.drawable.wartortle
            "Blastoise" -> R.drawable.blastoise
            "Articuno" -> R.drawable.articuno
            "Zapdos" -> R.drawable.zapdos
            "Moltres" -> R.drawable.moltres
            "Mewtwo" -> R.drawable.mewtwo
            "Mew" -> R.drawable.mew
            else -> R.drawable.pokemonhub
        }
    }
}