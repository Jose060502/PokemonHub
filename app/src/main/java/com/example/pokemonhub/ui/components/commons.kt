package com.example.pokemonhub.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pokemonhub.R
import com.example.pokemonhub.datamodel.ListModel
import com.example.pokemonhub.model.Pokemon
import com.example.pokemonhub.ui.theme.LocalExtendedColorScheme


@Composable
fun StandardInputTextComp(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {}
) {
    OutlinedTextField(
        modifier = modifier,
        singleLine = true,
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = label) }
    )
}

@Composable
fun ImageComp(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    drawable: Int,
    contentDesc: String = "",
    height: Int = 0,
    width: Int = 0
) {
    val contentDescription =
        if (contentDesc == "")
            stringResource(id = R.string.default_content_descrip)
        else
            contentDesc
    if (height != 0 && width != 0) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = contentDescription,
            modifier
                .height(height.dp)
                .width(width.dp),
            contentScale = contentScale
        )
    } else {
        Image(
            modifier = modifier,
            painter = painterResource(id = drawable),
            contentDescription = contentDescription,
            contentScale = contentScale
        )
    }
}

@Composable
fun StandardButtonComp(
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier
            .padding(8.dp),
        onClick = onClick,
        enabled = enabled
    ) {
        Text(text = label)
    }
}

@Composable
fun LabelAndValueComp(label: String, modifier: Modifier = Modifier, value: String = "") {
    Text(
        modifier = modifier,
        text = "$label = $value"
    )
}

@Composable
fun StandardTextComp(
    text: String,
    modifier: Modifier = Modifier,
    style: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.bodyMedium
) {
    Text(
        modifier = modifier,
        text = text,
        style = style
    )
}

@Composable
fun MedHeaderComp(title: String) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        shadowElevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.tertiary
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
        }
    }
}

@Composable
fun MedHeaderComp(title: String, modifier: Modifier = Modifier) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
        shadowElevation = 1.dp,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primary
    ) {
        Row {

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    modifier = Modifier.padding(8.dp),
                    text = title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}

@Composable
fun MedHeaderCompWithIcon(
    title: String,
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
        shadowElevation = 1.dp,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primary
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp)
            )


            IconButton(
                onClick = { onSearchClick() },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search),
                    tint = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}


@Composable
fun StandardText(label: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .padding(8.dp),
        style = MaterialTheme.typography.displayMedium,
        color = MaterialTheme.colorScheme.surface,
        maxLines = 100,
        text = label
    )
}

@Composable
fun StandardTextNull(label: String?, modifier: Modifier = Modifier) {
    if (label != null) {
        Text(
            modifier = modifier
                .padding(8.dp),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.surface,
            maxLines = 100,
            text = label
        )
    }
}

@Composable
fun StandardTextDetail(label: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .padding(8.dp),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.surface,
        maxLines = 100,
        text = label
    )
}

@Composable
fun StandardTextDetailFav(label: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .padding(8.dp),
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.surface,
        maxLines = 100,
        text = label
    )
}

@Composable
fun PokemonDetailFavText(label: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .padding(8.dp),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.surface,
        maxLines = 100,
        text = label
    )
}


@Composable
fun StandardButtonColor(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    val extendedColorScheme = LocalExtendedColorScheme.current
    Button(
        onClick = { onClick() },
        modifier = Modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = extendedColorScheme.onTertiary,
            contentColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Text(label)
    }
}

@Composable
fun BarraDetallesPokemon(title: String, pokemon: Pokemon, onClick: () -> Unit) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(pokemon.colorFondo),
        shadowElevation = 1.dp,
        shape = MaterialTheme.shapes.medium,
        color = pokemon.colorFondo
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(50.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                modifier = Modifier
                    .size(50.dp)
                    .clickable { onClick() },
                contentDescription = stringResource(R.string.opciones),
                tint = MaterialTheme.colorScheme.surface,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    modifier = Modifier.padding(8.dp),
                    text = title,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}

@Composable
fun BarraDetallesPokemonFavorito(title: String, favorite: ListModel, onClick: () -> Unit) {
    val bgColor = try {
        Color(android.graphics.Color.parseColor(favorite.colorHex))
    } catch (e: Exception) {
        Color.Gray
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(bgColor),
        shadowElevation = 1.dp,
        shape = MaterialTheme.shapes.medium,
        color = bgColor
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(50.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                modifier = Modifier
                    .size(50.dp)
                    .clickable { onClick() },
                contentDescription = stringResource(R.string.opciones),
                tint = MaterialTheme.colorScheme.surface,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    modifier = Modifier.padding(8.dp),
                    text = title,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}

@Composable
fun NotFoundDetail(name: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(R.string.loadingPokemonNotFound, name))
    }
}

@Composable
fun LoadingDetail(name: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(R.string.loadingPokemon, name))
    }
}

@Composable
fun LoadingList() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = colorResource(R.color.white)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(R.string.loadingList), color = colorResource(R.color.white))
    }
}

@Composable
fun LoadingListTablet() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(R.string.loadingList), color = MaterialTheme.colorScheme.onSurface)
    }
}