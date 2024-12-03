package com.example.pokemonhub.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pokemonhub.R
import com.example.pokemonhub.model.Datasource
import com.example.pokemonhub.model.Pokemon

@Composable
fun PokemonCard(pokemon: Pokemon){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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
                    imageVector = Icons.TwoTone.Favorite,
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

            }

            ImageComp(
                modifier = Modifier,
                drawable = Datasource.getDrawableIdByName(pokemon.photo),
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
                    imageVector = Icons.TwoTone.Favorite,
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
                drawable = Datasource.getDrawableIdByName(pokemon.photo),
                height = 100,
                width = 100
            )
        }
    }
}