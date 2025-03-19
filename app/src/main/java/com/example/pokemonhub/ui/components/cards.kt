package com.example.pokemonhub.ui.components

import android.app.Activity
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pokemonhub.R
import com.example.pokemonhub.datamodel.Comment
import com.example.pokemonhub.datamodel.PokeModel
import com.example.pokemonhub.model.Pokemon
import com.example.pokemonhub.ui.screens.favouritelist.PokemonFavListViewModel
import com.example.pokemonhub.ui.screens.profilescreen.ProfileViewModel

@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onClick: () -> Unit,
    favListViewModel: PokemonFavListViewModel
) {
    val context = LocalContext.current
    val view = LocalView.current
    val hexColor = String.format("#%08X", pokemon.colorFondo.toArgb())
    val alreadyFavoriteMsg = stringResource(R.string.already)
    val addedToFavoriteMsg = stringResource(R.string.addfavorite)
    if (!view.isInEditMode) {
        val activity = context as Activity
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = MaterialTheme.colorScheme.primary.toArgb()
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val insetsController = WindowInsetsControllerCompat(window, view)
            insetsController.isAppearanceLightStatusBars = MaterialTheme.colorScheme.primary.luminance() > 0.5f
        }

    }
    val favUiState by favListViewModel.uiState.collectAsState()
    val isAlreadyFavorite = favUiState.favorites.any { it.name == pokemon.name }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(
                onClick = {
                    if (isAlreadyFavorite) {
                        Toast.makeText(context, alreadyFavoriteMsg, Toast.LENGTH_SHORT).show()
                    } else {
                        val favoriteItem = PokeModel(
                            name = pokemon.name,
                            imageUrl = pokemon.imageUrl,
                            colorHex = hexColor,
                            types = pokemon.types,
                            abilities = pokemon.abilities,
                            stats = pokemon.stats
                        )
                        favListViewModel.insertFavourite(favoriteItem)
                        Toast.makeText(context, addedToFavoriteMsg, Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = if (isAlreadyFavorite) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = stringResource(R.string.favorito),
                    tint = if (isAlreadyFavorite) Color.Red else Color.Gray
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                StandardTextComp(
                    text = pokemon.name,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                StandardTextComp(
                    text = "Tipo: ${pokemon.types.joinToString(", ")}",
                    style = MaterialTheme.typography.titleSmall
                )
                StandardTextComp(
                    text = "Habilidad: ${pokemon.abilities.joinToString(", ")}",
                    style = MaterialTheme.typography.titleSmall
                )
            }

            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier
                    .size(120.dp),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.pokemonhub)
            )
        }
    }
}

@Composable
fun PokemonLandCard(
    pokemon: Pokemon,
    favListViewModel: PokemonFavListViewModel,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val view = LocalView.current
    val favUiState by favListViewModel.uiState.collectAsState()
    val isAlreadyFavorite = favUiState.favorites.any { it.name == pokemon.name }
    val alreadyfavorite = stringResource(R.string.already)
    val addedtofavorite = stringResource(R.string.addfavorite)

    // Manejo de color de la barra de estado (solo para versiones superiores a Lollipop)
    if (!view.isInEditMode) {
        val activity = context as Activity
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = MaterialTheme.colorScheme.primary.toArgb()
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val insetsController = WindowInsetsControllerCompat(window, view)
            insetsController.isAppearanceLightStatusBars = MaterialTheme.colorScheme.primary.luminance() > 0.5f
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            // Imagen del Pokémon
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier
                    .fillMaxHeight()
                    .size(150.dp),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.pokemonhub)
            )

            // Nombre del Pokémon
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                StandardTextComp(
                    text = pokemon.name,
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            // Manejo del botón de favorito
            IconButton(
                onClick = {
                    if (isAlreadyFavorite) {
                        Toast.makeText(context, alreadyfavorite, Toast.LENGTH_SHORT).show()
                    } else {
                        val favoriteItem = PokeModel(
                            name = pokemon.name,
                            imageUrl = pokemon.imageUrl,
                            colorHex = "", // Aquí puedes añadir un color si lo necesitas
                            types = pokemon.types,
                            abilities = pokemon.abilities,
                            stats = pokemon.stats
                        )
                        favListViewModel.insertFavourite(favoriteItem)
                        Toast.makeText(context, addedtofavorite, Toast.LENGTH_SHORT).show()
                    }
                },
            ) {
                if (isAlreadyFavorite) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        modifier = Modifier.size(48.dp),
                        contentDescription = stringResource(R.string.favorito),
                        tint = MaterialTheme.colorScheme.surface
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        modifier = Modifier.size(48.dp),
                        contentDescription = stringResource(R.string.favorito),
                        tint = MaterialTheme.colorScheme.surface
                    )
                }
            }
        }
    }
}


@Composable
fun PokemonFavouriteCard(
    pokemon: PokeModel,
    onClick: () -> Unit,
    onClickClear: () -> Unit
) {
    val context = LocalContext.current
    val view = LocalView.current

    if (!view.isInEditMode) {
        val activity = context as Activity
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = MaterialTheme.colorScheme.primary.toArgb()
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val insetsController = WindowInsetsControllerCompat(window, view)
            insetsController.isAppearanceLightStatusBars = MaterialTheme.colorScheme.primary.luminance() > 0.5f
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .fillMaxSize()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            Modifier.background(MaterialTheme.colorScheme.tertiaryContainer),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier
                    .fillMaxHeight()
                    .size(150.dp),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.pokemonhub)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                StandardTextNull(pokemon.name)
            }
            IconButton(
                onClick = { onClickClear() },
                modifier = Modifier.size(38.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    modifier = Modifier.size(38.dp),
                    contentDescription = stringResource(R.string.icono_eliminar),
                    tint = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}


@Composable
fun PokemonFavouriteLandCard(
    pokemon: PokeModel,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val view = LocalView.current

    // Manejo de la barra de estado (para versiones superiores a Lollipop)
    if (!view.isInEditMode) {
        val activity = context as Activity
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = MaterialTheme.colorScheme.primary.toArgb()
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val insetsController = WindowInsetsControllerCompat(window, view)
            insetsController.isAppearanceLightStatusBars = MaterialTheme.colorScheme.primary.luminance() > 0.5f
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(150.dp)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(android.graphics.Color.parseColor(pokemon.colorHex))),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            // Imagen del Pokémon
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.pokemonhub)
            )

            // Nombre del Pokémon
            StandardTextNull(pokemon.name, Modifier.weight(0.5f))

            // Información del Pokémon (tipos y habilidades)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StandardText(stringResource(R.string.tipoP, pokemon.types.joinToString(", ")))
                StandardText(stringResource(R.string.habilidadP, pokemon.abilities.joinToString(", ")))
            }

            // Icono de eliminación (cuando se está trabajando con favoritos)
            IconButton(
                onClick = {
                    // Lógica para eliminar el Pokémon de favoritos aquí
                    Toast.makeText(context, "Eliminado de favoritos", Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    modifier = Modifier.size(48.dp),
                    contentDescription = stringResource(R.string.icono_eliminar),
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}

@Composable
fun PokemonDetailCard(
    pokemon: Pokemon,  // Usamos ListModel aquí
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val view = LocalView.current

    // Manejo de la barra de estado (para versiones superiores a Lollipop)
    if (!view.isInEditMode) {
        val activity = context as Activity
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Usamos colorHex de ListModel y lo convertimos a Color
            window.statusBarColor = pokemon.colorFondo.toArgb()
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val insetsController = WindowInsetsControllerCompat(window, view)
            insetsController.isAppearanceLightStatusBars = pokemon.colorFondo.luminance() > 0.5f
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = MaterialTheme.shapes.extraSmall
    ) {
        BarraDetallesPokemon(stringResource(R.string.detalles_pokemon), pokemon, onBackClick)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(pokemon.colorFondo) // Usamos el color del Pokémon
                .padding(8.dp)
        ) {
            // Imagen del Pokémon
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Fit,
                error = painterResource(id = R.drawable.pokemonhub)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Nombre del Pokémon
            StandardTextDetail(stringResource(R.string.nombreDetail, pokemon.name))

            Spacer(modifier = Modifier.height(8.dp))

            // Tipos de Pokémon
            if (pokemon.types.isEmpty()) {
                StandardTextDetailFav(stringResource(R.string.noTypes))
            } else {
                StandardTextDetailFav(stringResource(R.string.typeDetail, pokemon.types.joinToString(", ")))
            }

            // Habilidades de Pokémon
            if (pokemon.abilities.isEmpty()) {
                StandardTextDetailFav(stringResource(R.string.noAbilities))
            } else {
                StandardTextDetailFav(stringResource(R.string.abilityDetail, pokemon.abilities.joinToString(", ")))
            }

            // Estadísticas de Pokémon
            if (pokemon.stats.isEmpty()) {
                StandardTextDetailFav(stringResource(R.string.noStats))
            } else {
                StandardTextDetailFav(stringResource(R.string.statsDetail, pokemon.stats.joinToString(", ")))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botón de agregar a favoritos
            Button(
                onClick = onFavoriteClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                StandardTextDetail(stringResource(R.string.favorito))
            }
        }
    }
}

@Composable
fun PokemonDetailCardLand(
    pokemon: Pokemon,  // Usamos el modelo Pokemon aquí
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val view = LocalView.current

    // Manejo de la barra de estado (para versiones superiores a Lollipop)
    if (!view.isInEditMode) {
        val activity = context as Activity
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Usamos colorFondo de Pokemon y lo convertimos a Color
            window.statusBarColor = pokemon.colorFondo.toArgb()
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val insetsController = WindowInsetsControllerCompat(window, view)
            insetsController.isAppearanceLightStatusBars = pokemon.colorFondo.luminance() > 0.5f
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp),
        shape = MaterialTheme.shapes.extraSmall
    ) {
        BarraDetallesPokemon(stringResource(R.string.detalles_pokemon), pokemon, onBackClick)
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(pokemon.colorFondo)  // Usamos el colorFondo del Pokémon
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model = pokemon.imageUrl,
                    contentDescription = pokemon.name,
                    modifier = Modifier.size(350.dp),
                    contentScale = ContentScale.Fit,
                    error = painterResource(id = R.drawable.pokemonhub)
                )

                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    StandardText(stringResource(R.string.nombreDetail, pokemon.name))
                    if (pokemon.types.isEmpty()) {
                        StandardTextDetail(stringResource(R.string.noTypes))
                    } else {
                        StandardTextDetail(
                            stringResource(
                                R.string.typeDetail,
                                pokemon.types.joinToString(", ")
                            )
                        )
                    }
                    if (pokemon.abilities.isEmpty()) {
                        StandardTextDetail(stringResource(R.string.noAbilities))
                    } else {
                        StandardTextDetail(
                            stringResource(
                                R.string.abilityDetail,
                                pokemon.abilities.joinToString(", ")
                            )
                        )
                    }

                    if (pokemon.stats.isEmpty()) {
                        StandardTextDetail(stringResource(R.string.noStats))
                    } else {
                        StandardTextDetail(
                            stringResource(
                                R.string.statsDetail,
                                pokemon.stats.joinToString(", ")
                            )
                        )
                    }
                }
            }
        }
        Button(
            onClick = onFavoriteClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            StandardTextDetail(stringResource(R.string.favorito))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailFavCard(
    favorite: PokeModel, // Usamos ListModel
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    profileViewModel: ProfileViewModel = viewModel(factory = ProfileViewModel.Factory),
    pokemonFavListScreenViewModel: PokemonFavListViewModel = viewModel(),
) {
    val profileUiState by profileViewModel.uiState.collectAsState()
    val username = profileUiState.nombreUsuario
    val commentsFlow = pokemonFavListScreenViewModel.getCommentsForFavorite(favorite.name ?: "")
    val comments by commentsFlow.collectAsState(initial = emptyList())
    var showDialog by remember { mutableStateOf(false) }
    var commentText by remember { mutableStateOf("") }
    val context = LocalContext.current
    val view = LocalView.current

    // Convertir el color de fondo desde el ListModel
    val bgColor = try {
        Color(android.graphics.Color.parseColor(favorite.colorHex ?: "#FF000000"))
    } catch (e: Exception) {
        colorResource(R.color.purple_500)
    }
    val bgColorLuminance = bgColor

    // Para la barra de estado
    if (!view.isInEditMode) {
        val activity = context as Activity
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = bgColor.toArgb()
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val insetsController = WindowInsetsControllerCompat(window, view)
            insetsController.isAppearanceLightStatusBars = bgColorLuminance.luminance() > 0.5f
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        // Barra de detalles
        BarraDetallesPokemonFavorito(stringResource(R.string.detail_fav), favorite, onBackClick)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(bgColor)
                .padding(8.dp)
        ) {
            // Mostrar la imagen del Pokémon
            AsyncImage(
                model = favorite.imageUrl,
                contentDescription = favorite.name,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.pokemonhub)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nombre del Pokémon
            StandardText(
                stringResource(
                    R.string.nombreDetail,
                    favorite.name ?: stringResource(R.string.noname)
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Videojuegos relacionados
            if (favorite.abilities.isEmpty()) {
                PokemonDetailFavText(stringResource(R.string.noAbilities))
            } else {
                PokemonDetailFavText(
                    stringResource(
                        R.string.abilityDetail,
                        favorite.abilities.joinToString(", ")
                    )
                )
            }

            if (favorite.types.isEmpty()) {
                PokemonDetailFavText(stringResource(R.string.noTypes))
            } else {
                PokemonDetailFavText(stringResource(R.string.typeDetail, favorite.types.joinToString(", ")))
            }

            if (favorite.stats.isEmpty()) {
                PokemonDetailFavText(stringResource(R.string.noStats))
            } else {
                PokemonDetailFavText(stringResource(R.string.statsDetail, favorite.stats.joinToString(", ")))
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Comentarios
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                StandardText(stringResource(R.string.listofcomment))
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(end = 8.dp)
            ) {
                items(comments) { commentText ->
                    CommentCard(commentText)
                }
            }

            // Botón para agregar un comentario
            FloatingActionButton(
                onClick = { showDialog = true },
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.End),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.add_comment),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            // Diálogo para agregar un comentario
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text(stringResource(R.string.add_comment)) },
                    text = {
                        TextField(
                            value = commentText,
                            onValueChange = { commentText = it },
                            label =  {Text(stringResource(R.string.comment)) }
                        )
                    },
                    confirmButton = {
                        Button(onClick = {
                            pokemonFavListScreenViewModel.addCommentToFavorite(
                                favorite.name ?: "", // Usamos el nombre del Pokémon desde ListModel
                                username,
                                commentText
                            )
                            commentText = ""
                            showDialog = false
                        }) {
                            Text(stringResource(R.string.save))
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showDialog = false }) {
                            Text(stringResource(R.string.cancel))
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun CommentCard(comment: Comment, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = comment.userName,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = comment.commentText,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

