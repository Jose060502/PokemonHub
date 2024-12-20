package com.example.pokemonhub.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.twotone.Clear
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.FavoriteBorder
import androidx.compose.material.icons.twotone.KeyboardArrowDown
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pokemonhub.R
import com.example.pokemonhub.model.Datasource
import com.example.pokemonhub.model.Pokemon

@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onClick: () -> Unit,
    isFavorite: Boolean,
    onFavoriteClick: (Boolean) -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(
                onClick = { onFavoriteClick(!isFavorite) },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) {
                        Icons.Default.Favorite // Icono de favorito
                    } else {
                        Icons.Default.FavoriteBorder // Icono de no favorito
                    },
                    contentDescription = "Favorito",
                    tint = if (isFavorite) Color.Red else Color.Gray
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                StandardTextComp(
                    text = pokemon.name,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                StandardTextComp(
                    text = "Tipo: ${pokemon.type}",
                    style = MaterialTheme.typography.titleSmall
                )
                StandardTextComp(
                    text = "Habilidad: ${pokemon.ability}",
                    style = MaterialTheme.typography.titleSmall
                )

            }

            ImageComp(
                modifier = Modifier,
                drawable = Datasource.getDrawableIdByName(pokemon.sprite),
                height = 100,
                width = 100
            )
        }
    }
}

@Composable
fun PokemonLandCard(pokemon: Pokemon){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(
                onClick = { /* Acción futura, como mostrar más información del héroe */ },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.TwoTone.Star,
                    //painter = painterResource(R.drawable.remove_24px), //Icono descargado de Icons de Material.
                    modifier = Modifier.size(48.dp),
                    contentDescription = stringResource(R.string.more_content_desc),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                StandardTextComp(
                    text = pokemon.name,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                StandardTextComp(
                    text = "Tipo: ${pokemon.type}",
                    style = MaterialTheme.typography.headlineMedium
                )
                StandardTextComp(
                    text = "Habilidad: ${pokemon.ability}",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                StandardTextComp(
                    text = stringResource(R.string.pokemon_description, pokemon.description),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

            }

            ImageComp(
                modifier = Modifier,
                drawable = Datasource.getDrawableIdByName(pokemon.sprite),
                height = 100,
                width = 100
            )
        }
    }
}

//@Composable
//fun PokemonDetailsCard(pokemon: Pokemon){
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.tertiaryContainer
//        ),
//        shape = MaterialTheme.shapes.medium
//    ) {
//        IconButton(
//            onClick = { /* Acción futura, como mostrar más información del héroe */ },
//            modifier = Modifier
//                .size(48.dp)
//        ) {
//            Icon(
//                imageVector = Icons.TwoTone.Star,
//                modifier = Modifier.size(48.dp),
//                contentDescription = stringResource(R.string.more_content_desc),
//                tint = MaterialTheme.colorScheme.primary
//            )
//        }
//        Column(
//            modifier = Modifier.padding(8.dp)
//        ) {
//            // Mostramos los sprites
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 8.dp),
//                horizontalArrangement = Arrangement.Center
//            ) {
//                ImageComp(
//                    modifier = Modifier.size(100.dp),
//                    drawable = Datasource.getDrawableIdByName(pokemon.sprite),
//                    height = 100,
//                    width = 100
//                )
//                Text(
//                    text = "Normal",
//                    style = MaterialTheme.typography.bodyMedium,
//                    modifier = Modifier.padding(top = 4.dp)
//                )
//                Spacer(modifier = Modifier.width(40.dp))
//                ImageComp(
//                    modifier = Modifier.size(100.dp),
//                    drawable = Datasource.getDrawableIdByName(pokemon.spriteShiny),
//                    height = 100,
//                    width = 100
//                )
//                Text(
//                    text = "Variocolor",
//                    style = MaterialTheme.typography.bodyMedium,
//                    modifier = Modifier.padding(top = 4.dp)
//                )
//            }
//
//
//            // Información adicional sobre el Pokémon
//            StandardTextComp(
//                text = pokemon.name,
//                style = MaterialTheme.typography.headlineMedium
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//            StandardTextComp(
//                text = "Tipo: ${pokemon.type}",
//                style = MaterialTheme.typography.titleLarge
//            )
//            StandardTextComp(
//                text = "Habilidad: ${pokemon.ability}",
//                style = MaterialTheme.typography.titleLarge
//            )
//            StandardTextComp(
//                text = "Habilidad oculta: ${pokemon.hiddenAbility}",
//                style = MaterialTheme.typography.titleLarge
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            StandardTextComp(
//                text = "Pokedex #: ${pokemon.pokedexNumber}",
//                style = MaterialTheme.typography.titleSmall
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            StandardTextComp(
//                text = "Stats:",
//                style = MaterialTheme.typography.titleSmall
//            )
//            StandardTextComp(
//                text = "HP: ${pokemon.baseHp}, Attack: ${pokemon.baseAttack}, Defense: ${pokemon.baseDefense}",
//                style = MaterialTheme.typography.titleMedium
//            )
//            StandardTextComp(
//                text = "Sp. Atk: ${pokemon.baseSpAtk}, Sp. Def: ${pokemon.baseSpDef}, Speed: ${pokemon.baseSpeed}",
//                style = MaterialTheme.typography.titleMedium
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//            StandardTextComp(
//                text = stringResource(R.string.pokemon_description, pokemon.description),
//                style = MaterialTheme.typography.titleSmall
//            )
//        }
//    }
//}

//@Composable
//fun PokemonFavouriteDetailsCard(pokemon: Pokemon){
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.tertiaryContainer
//        ),
//        shape = MaterialTheme.shapes.medium
//    ) {
//        IconButton(
//            onClick = { /* Acción futura, como mostrar más información del héroe */ },
//            modifier = Modifier
//                .size(48.dp)
//        ) {
//            Icon(
//                imageVector = Icons.TwoTone.Clear,
//                modifier = Modifier.size(48.dp),
//                contentDescription = stringResource(R.string.more_content_desc),
//                tint = MaterialTheme.colorScheme.primary
//            )
//        }
//        Column(
//            modifier = Modifier.padding(8.dp)
//        ) {
//            // Mostramos los sprites
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 8.dp),
//                horizontalArrangement = Arrangement.Center
//            ) {
//                ImageComp(
//                    modifier = Modifier.size(100.dp),
//                    drawable = Datasource.getDrawableIdByName(pokemon.sprite),
//                    height = 100,
//                    width = 100
//                )
//                Text(
//                    text = "Normal",
//                    style = MaterialTheme.typography.bodyMedium,
//                    modifier = Modifier.padding(top = 4.dp)
//                )
//                Spacer(modifier = Modifier.width(40.dp))
//                ImageComp(
//                    modifier = Modifier.size(100.dp),
//                    drawable = Datasource.getDrawableIdByName(pokemon.spriteShiny),
//                    height = 100,
//                    width = 100
//                )
//                Text(
//                    text = "Variocolor",
//                    style = MaterialTheme.typography.bodyMedium,
//                    modifier = Modifier.padding(top = 4.dp)
//                )
//            }
//
//
//            // Información adicional sobre el Pokémon
//            StandardTextComp(
//                text = pokemon.name,
//                style = MaterialTheme.typography.headlineMedium
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//            StandardTextComp(
//                text = "Tipo: ${pokemon.type}",
//                style = MaterialTheme.typography.titleLarge
//            )
//            StandardTextComp(
//                text = "Habilidad: ${pokemon.ability}",
//                style = MaterialTheme.typography.titleLarge
//            )
//            StandardTextComp(
//                text = "Habilidad oculta: ${pokemon.hiddenAbility}",
//                style = MaterialTheme.typography.titleLarge
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            StandardTextComp(
//                text = "Pokedex #: ${pokemon.pokedexNumber}",
//                style = MaterialTheme.typography.titleSmall
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            StandardTextComp(
//                text = "Stats:",
//                style = MaterialTheme.typography.titleSmall
//            )
//            StandardTextComp(
//                text = "HP: ${pokemon.baseHp}, Attack: ${pokemon.baseAttack}, Defense: ${pokemon.baseDefense}",
//                style = MaterialTheme.typography.titleMedium
//            )
//            StandardTextComp(
//                text = "Sp. Atk: ${pokemon.baseSpAtk}, Sp. Def: ${pokemon.baseSpDef}, Speed: ${pokemon.baseSpeed}",
//                style = MaterialTheme.typography.titleMedium
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//            StandardTextComp(
//                text = stringResource(R.string.pokemon_description, pokemon.description),
//                style = MaterialTheme.typography.titleSmall
//            )
//        }
//    }
//}



@Composable
fun PokemonFavouriteCard(pokemon: Pokemon, onClickClear: () -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(
                onClick = { onClickClear() },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.TwoTone.Clear,
                    //painter = painterResource(R.drawable.remove_24px), //Icono descargado de Icons de Material.
                    modifier = Modifier.size(48.dp),
                    contentDescription = stringResource(R.string.more_content_desc),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                StandardTextComp(
                    text = pokemon.name,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                StandardTextComp(
                    text = "Tipo: ${pokemon.type}",
                    style = MaterialTheme.typography.titleSmall
                )
                StandardTextComp(
                    text = "Habilidad: ${pokemon.ability}",
                    style = MaterialTheme.typography.titleSmall
                )

            }

            ImageComp(
                modifier = Modifier,
                drawable = Datasource.getDrawableIdByName(pokemon.sprite),
                height = 100,
                width = 100
            )
        }
    }
}

@Composable
fun PokemonFavouriteLandCard(pokemon: Pokemon){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(
                onClick = { /* Acción futura, como mostrar más información del héroe */ },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.TwoTone.Clear,
                    //painter = painterResource(R.drawable.remove_24px), //Icono descargado de Icons de Material.
                    modifier = Modifier.size(48.dp),
                    contentDescription = stringResource(R.string.more_content_desc),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                StandardTextComp(
                    text = pokemon.name,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                StandardTextComp(
                    text = "Tipo: ${pokemon.type}",
                    style = MaterialTheme.typography.headlineMedium
                )
                StandardTextComp(
                    text = "Habilidad: ${pokemon.ability}",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                StandardTextComp(
                    text = stringResource(R.string.pokemon_description, pokemon.description),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

            }

            ImageComp(
                modifier = Modifier,
                drawable = Datasource.getDrawableIdByName(pokemon.sprite),
                height = 100,
                width = 100
            )
        }
    }
}