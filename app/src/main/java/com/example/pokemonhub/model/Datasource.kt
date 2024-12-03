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

    val pokemonList: () -> MutableList<Pokemon> = {
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
            Pokemon(
                "Charmeleon",
                "Fuego",
                5,
                "Mar llamas",
                "Poder solar",
                58,
                64,
                58,
                80,
                65,
                80,
                "Charmeleon",
                "Cuando balancea su ardiente cola, eleva la temperatura a niveles muy altos."
            ),
            Pokemon(
                "Charizard",
                "Fuego-Volador",
                6,
                "Mar llamas",
                "Poder Solar",
                78,
                84,
                78,
                109,
                85,
                100,
                "Charizard",
                "Escupe fuego tan caliente que funde las rocas. Causa incendios forestales sin querer."
            ),
            Pokemon(
                "Squirtle",
                "Agua",
                7,
                "Torrente",
                "Cura lluvia",
                44,
                48,
                65,
                50,
                64,
                43,
                "Squirtle",
                "Tras nacer, su espalda se hincha y endurece como una concha. Echa potente espuma por la boca."
            ),
            Pokemon(
                "Wartortle",
                "Agua",
                8,
                "Torrente",
                "Cura lluvia",
                59,
                63,
                80,
                65,
                80,
                58,
                "Wartortle",
                "Se oculta en el agua para cazar a sus presas. Al nadar rápidamente, mueve sus orejas para nivelarse."
            ),
            Pokemon(
                "Blastoise",
                "Agua",
                9,
                "Torrente",
                "Cura lluvia",
                79,
                83,
                100,
                85,
                105,
                78,
                "Blastoise",
                "Un brutal Pokémon con reactores de agua en su caparazón. Éstos son usados para rápidos placajes."
            ),
            Pokemon(
                "Articuno",
                "Hielo-Volador",
                144,
                "Presión",
                "Manto Níveo",
                90,
                85,
                100,
                95,
                125,
                85,
                "Articuno",
                "Un legendario pájaro Pokémon. Se aparece a la gente que se ha perdido en las heladas montañas."
            ),
            Pokemon(
                "Zapdos",
                "Electrico-Volador",
                145,
                "Presión",
                "Pararrayos",
                90,
                90,
                85,
                125,
                90,
                100,
                "Zapdos",
                "Un legendario pájaro Pokémon. Dicen que aparece entre las nubes lanzando enormes rayos brillantes."
            ),
            Pokemon(
                "Moltres",
                "Fuego-Volador",
                146,
                "Presión",
                "Cuerpo llama",
                90,
                100,
                90,
                125,
                85,
                90,
                "Moltres",
                "Más conocido como el legendario pájaro de fuego. Con cada aleteo crea brillantes llamas."
            ),
            Pokemon(
                "Mewtwo",
                "Psíquico",
                150,
                "Presión",
                "Nerviosismo",
                106,
                110,
                90,
                154,
                90,
                130,
                "Mewtwo",
                "Fue creado por un científico tras años de horribles experimentos de ingeniería genética."
            ),
            Pokemon(
                "Mew",
                "Psíquico",
                151,
                "Sincronía",
                "",
                100,
                100,
                100,
                100,
                100,
                100,
                "Mew",
                "Es tan raro que muchos expertos opinan que es solo una ilusión. Solo unos pocos lo han visto."
            ),
        )
    }

    val getListXtimes: (Int) -> MutableList<Pokemon> = { times ->
        val list = mutableListOf<Pokemon>()
        for (i in 1..times) {
            list.addAll(pokemonList())
        }
        list
    }

    val getPokemonByName: (String) -> Pokemon? = { name ->
        pokemonList().find { it.name == name } ?: null
    }

    val getSomeRandPokemon: (Int) -> MutableList<Pokemon> = { num ->
        val pokemon = pokemonList()
        if (num <= pokemon.size) pokemon.subList(0, num)
        pokemon
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