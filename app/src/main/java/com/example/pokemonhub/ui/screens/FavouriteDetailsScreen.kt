package com.example.pokemonhub.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Clear
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokemonhub.R
import com.example.pokemonhub.model.Datasource
import com.example.pokemonhub.model.Pokemon
import com.example.pokemonhub.ui.components.ImageComp
import com.example.pokemonhub.ui.components.MedHeaderComp
import com.example.pokemonhub.ui.components.PokemonFavouriteCard
import com.example.pokemonhub.ui.components.PokemonFavouriteDetailsCard
import com.example.pokemonhub.ui.components.PokemonFavouriteLandCard
import com.example.pokemonhub.ui.components.StandardTextComp


@Composable
fun PokemonFavouriteDetailsListCompactScreen(pokemon_name: String, navController: NavController, modifier: Modifier = Modifier) {
    val pokemon = Datasource.getPokemonByName(pokemon_name)
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        pokemon?.let {
            IconButton(
                onClick = { /* Acción futura, como mostrar más información del héroe */ },
                modifier = Modifier
                    .size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.TwoTone.Clear,
                    modifier = Modifier.size(48.dp),
                    contentDescription = stringResource(R.string.more_content_desc),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                // Mostramos los sprites
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ImageComp(
                        modifier = Modifier.size(100.dp),
                        drawable = Datasource.getDrawableIdByName(pokemon.sprite),
                        height = 100,
                        width = 100
                    )
                    Text(
                        text = "Normal",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Spacer(modifier = Modifier.width(40.dp))
                    ImageComp(
                        modifier = Modifier.size(100.dp),
                        drawable = Datasource.getDrawableIdByName(pokemon.spriteShiny),
                        height = 100,
                        width = 100
                    )
                    Text(
                        text = "Variocolor",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }


                // Información adicional sobre el Pokémon
                StandardTextComp(
                    text = pokemon.name,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                StandardTextComp(
                    text = "Tipo: ${pokemon.type}",
                    style = MaterialTheme.typography.titleLarge
                )
                StandardTextComp(
                    text = "Habilidad: ${pokemon.ability}",
                    style = MaterialTheme.typography.titleLarge
                )
                StandardTextComp(
                    text = "Habilidad oculta: ${pokemon.hiddenAbility}",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                StandardTextComp(
                    text = "Pokedex #: ${pokemon.pokedexNumber}",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                StandardTextComp(
                    text = "Stats:",
                    style = MaterialTheme.typography.titleSmall
                )
                StandardTextComp(
                    text = "HP: ${pokemon.baseHp}, Attack: ${pokemon.baseAttack}, Defense: ${pokemon.baseDefense}",
                    style = MaterialTheme.typography.titleMedium
                )
                StandardTextComp(
                    text = "Sp. Atk: ${pokemon.baseSpAtk}, Sp. Def: ${pokemon.baseSpDef}, Speed: ${pokemon.baseSpeed}",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))
                StandardTextComp(
                    text = stringResource(R.string.pokemon_description, pokemon.description),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }?: StandardTextComp(stringResource(R.string.pokemon_not_found), style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun PokemonFavouriteDetailsListMedExpScreen(pokemon_name: String, navController: NavController, modifier: Modifier = Modifier){
    val pokemon = Datasource.getPokemonByName(pokemon_name)
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        pokemon?.let {
            IconButton(
                onClick = { /* Acción futura, como mostrar más información del héroe */ },
                modifier = Modifier
                    .size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.TwoTone.Clear,
                    modifier = Modifier.size(48.dp),
                    contentDescription = stringResource(R.string.more_content_desc),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                // Mostramos los sprites
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ImageComp(
                        modifier = Modifier.size(100.dp),
                        drawable = Datasource.getDrawableIdByName(pokemon.sprite),
                        height = 100,
                        width = 100
                    )
                    Text(
                        text = "Normal",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Spacer(modifier = Modifier.width(40.dp))
                    ImageComp(
                        modifier = Modifier.size(100.dp),
                        drawable = Datasource.getDrawableIdByName(pokemon.spriteShiny),
                        height = 100,
                        width = 100
                    )
                    Text(
                        text = "Variocolor",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }


                // Información adicional sobre el Pokémon
                StandardTextComp(
                    text = pokemon.name,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                StandardTextComp(
                    text = "Tipo: ${pokemon.type}",
                    style = MaterialTheme.typography.titleLarge
                )
                StandardTextComp(
                    text = "Habilidad: ${pokemon.ability}",
                    style = MaterialTheme.typography.titleLarge
                )
                StandardTextComp(
                    text = "Habilidad oculta: ${pokemon.hiddenAbility}",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                StandardTextComp(
                    text = "Pokedex #: ${pokemon.pokedexNumber}",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                StandardTextComp(
                    text = "Stats:",
                    style = MaterialTheme.typography.titleSmall
                )
                StandardTextComp(
                    text = "HP: ${pokemon.baseHp}, Attack: ${pokemon.baseAttack}, Defense: ${pokemon.baseDefense}",
                    style = MaterialTheme.typography.titleMedium
                )
                StandardTextComp(
                    text = "Sp. Atk: ${pokemon.baseSpAtk}, Sp. Def: ${pokemon.baseSpDef}, Speed: ${pokemon.baseSpeed}",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))
                StandardTextComp(
                    text = stringResource(R.string.pokemon_description, pokemon.description),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }?: StandardTextComp(stringResource(R.string.pokemon_not_found), style = MaterialTheme.typography.headlineMedium)
    }
}