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
                "BulbasaurShiny",
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
                "IvysaurShiny",
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
                "VenusaurShiny",
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
                "CharmanderShiny",
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
                "CharmeleonShiny",
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
                "CharizardShiny",
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
                "SquirtleShiny",
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
                "WartortleShiny",
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
                "BlastoiseShiny",
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
                "ArticunoShiny",
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
                "ZapdosShiny",
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
                "MoltresShiny",
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
                "MewtwoShiny",
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
                "MewShiny",
                "Es tan raro que muchos expertos opinan que es solo una ilusión. Solo unos pocos lo han visto."
            ),
        )
    }

    fun getFavoriteDetailsPokemons(): List<Pokemon> {
        return listOf(
            // Bulbasaur
            getPokemonByName("Bulbasaur")!!.apply {
                comments = listOf(
                    "Me encanta Bulbasaur porque es uno de los primeros Pokémon, ¡y la idea de tener una planta en la espalda me parece genial!",
                    "Bulbasaur tiene un diseño muy sencillo pero encantador, y es uno de los más accesibles para los principiantes.",
                    "Amo la combinación de tipo Planta y Veneno, ¡y su evolución a Venusaur es impresionante!",
                    "Es mi Pokémon favorito porque es fiel y siempre me ha acompañado en mis juegos. Además, ¡tiene un gran potencial competitivo!",
                    "Bulbasaur tiene un aspecto tan adorable, siempre me hace pensar en los días de infancia jugando Pokémon."
                )
            },

            // Charmander
            getPokemonByName("Charmander")!!.apply {
                comments = listOf(
                    "Charmander es mi favorito porque el fuego siempre me ha fascinado. ¡Su cola es lo más!",
                    "Me encanta cómo Charmander evoluciona en Charizard, uno de los Pokémon más poderosos de todos.",
                    "Siempre he sido fan de los Pokémon de tipo fuego, y Charmander es el comienzo perfecto para un entrenador.",
                    "Es un Pokémon con una gran historia detrás, y su evolución a Charizard es épica. Siempre ha sido mi compañero fiel.",
                    "Charmander es un Pokémon con mucha energía y tiene una de las evoluciones más poderosas de la serie."
                )
            },

            // Squirtle
            getPokemonByName("Squirtle")!!.apply {
                comments = listOf(
                    "Squirtle siempre ha sido mi elección porque me gustan mucho los Pokémon de agua, y su diseño es supercool.",
                    "Me gusta Squirtle porque es un Pokémon que tiene una evolución increíble, ¡Blastoise es imbatible!",
                    "Es tan tierno y su habilidad en batallas es excelente. ¡Además, me encanta su actitud en el anime!",
                    "Siempre elegí a Squirtle en los juegos originales. ¡El poder del agua es tan versátil en combate!",
                    "Squirtle me recuerda a mis primeras experiencias jugando Pokémon. ¡Un clásico que nunca pasa de moda!"
                )
            },

            // Zapdos
            getPokemonByName("Zapdos")!!.apply {
                comments = listOf(
                    "Zapdos es uno de los pájaros legendarios, y su habilidad eléctrica es algo único. ¡Es increíblemente poderoso!",
                    "Me encanta el diseño de Zapdos, es como un rayo viviente. Siempre lo he considerado uno de los Pokémon más majestuosos.",
                    "Zapdos es uno de los mejores Pokémon para cualquier entrenador que quiera una ventaja eléctrica en sus batallas.",
                    "Es mi Pokémon legendario favorito, su poder y su forma imponente son únicos en la serie.",
                    "El hecho de que Zapdos sea tan raro y potente lo hace aún más especial. ¡Siempre he querido tenerlo en mi equipo!"
                )
            },

            // Mewtwo
            getPokemonByName("Mewtwo")!!.apply {
                comments = listOf(
                    "Mewtwo es increíble, es uno de los Pokémon más poderosos y tiene una historia fascinante en el anime.",
                    "La combinación de ser un Pokémon psíquico y su origen genético lo hacen un personaje muy intrigante. ¡Es un verdadero líder!",
                    "Su poder y habilidades psíquicas lo hacen un rival formidable. ¡Siempre lo he querido en mi equipo por su increíble poder!",
                    "Mewtwo tiene una historia única. Me encanta cómo su personalidad se desarrolla en las películas y juegos.",
                    "Es un Pokémon tan impresionante, su diseño y su poder psíquico lo convierten en uno de los más icónicos de todos."
                )
            },

            // Charizard
            getPokemonByName("Charizard")!!.apply {
                comments = listOf(
                    "Charizard es uno de los Pokémon más emblemáticos. Su presencia en batalla es imponente y siempre me ha fascinado.",
                    "Siempre he sido un gran fan de los Pokémon de tipo fuego, y Charizard es el rey de todos ellos.",
                    "Charizard tiene una gran historia detrás, su evolución de Charmander a Charizard es épica, y su poder es inmenso.",
                    "Lo adoro porque es un Pokémon versátil que puede hacer frente a muchas situaciones en batalla. ¡Es un clásico!",
                    "Charizard tiene uno de los diseños más geniales de todos los Pokémon. Además, ¡es un icono de la franquicia!"
                )
            }
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
        val pokemon = pokemonList() // Obtiene la lista completa
        if (num <= pokemon.size) {
            pokemon.shuffled().take(num).toMutableList() // Baraja y toma `num` elementos
        } else {
            pokemon.shuffled().toMutableList() // Baraja y devuelve todos los elementos
        }
    }



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