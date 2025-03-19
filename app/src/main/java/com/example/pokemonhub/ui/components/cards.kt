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
    pokemon: Pokemon, // Parámetro que recibe un objeto de tipo Pokemon que contiene los datos a mostrar.
    onClick: () -> Unit, // Función que se ejecutará cuando se haga clic en la tarjeta del Pokémon.
    favListViewModel: PokemonFavListViewModel // ViewModel que gestiona los Pokémon favoritos.
) {
    // Se obtiene el contexto de la aplicación y la vista actual.
    val context = LocalContext.current
    val view = LocalView.current

    // Convertimos el color de fondo del Pokémon a su formato hexadecimal para utilizarlo en la UI.
    val hexColor = String.format("#%08X", pokemon.colorFondo.toArgb())

    // Mensajes que se mostrarán si el Pokémon ya es favorito o si se ha añadido correctamente.
    val alreadyFavoriteMsg = stringResource(R.string.already)
    val addedToFavoriteMsg = stringResource(R.string.addfavorite)

    // Comprobación para personalizar la barra de estado en versiones superiores a Lollipop (API 21).
    if (!view.isInEditMode) {
        val activity = context as Activity
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Cambiar el color de la barra de estado a color primario.
            window.statusBarColor = MaterialTheme.colorScheme.primary.toArgb()
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val insetsController = WindowInsetsControllerCompat(window, view)
            // Ajustar el color de la barra de estado según el luminancia del color primario.
            insetsController.isAppearanceLightStatusBars =
                MaterialTheme.colorScheme.primary.luminance() > 0.5f
        }
    }

    // Observamos el estado del ViewModel para obtener la lista de favoritos actualizada.
    val favUiState by favListViewModel.uiState.collectAsState()

    // Verificamos si el Pokémon ya está en la lista de favoritos.
    val isAlreadyFavorite = favUiState.favorites.any { it.name == pokemon.name }

    // Comienza la definición de la tarjeta (Card) que contendrá los datos del Pokémon.
    Card(
        modifier = Modifier
            .fillMaxWidth() // La tarjeta ocupa todo el ancho disponible.
            .padding(8.dp) // Espaciado alrededor de la tarjeta.
            .clickable { onClick() }, // Acción de clic en la tarjeta.
        shape = MaterialTheme.shapes.medium, // Bordes redondeados para la tarjeta.
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer // Color de fondo de la tarjeta.
        )
    ) {
        // Contenedor de la tarjeta en formato fila (Row).
        Row(
            modifier = Modifier.padding(8.dp), // Espaciado dentro de la fila.
            verticalAlignment = Alignment.CenterVertically, // Alineación vertical.
            horizontalArrangement = Arrangement.SpaceAround // Espaciado horizontal entre los elementos.
        ) {
            // Botón para agregar/eliminar de favoritos.
            IconButton(
                onClick = {
                    // Si el Pokémon ya está en favoritos, mostrar mensaje de ya añadido.
                    if (isAlreadyFavorite) {
                        Toast.makeText(context, alreadyFavoriteMsg, Toast.LENGTH_SHORT).show()
                    } else {
                        // Si no está en favoritos, agregamos el Pokémon a la lista de favoritos usando el ViewModel.
                        val favoriteItem = PokeModel(
                            name = pokemon.name,
                            imageUrl = pokemon.imageUrl,
                            colorHex = hexColor,
                            types = pokemon.types,
                            abilities = pokemon.abilities,
                            stats = pokemon.stats
                        )
                        favListViewModel.insertFavourite(favoriteItem) // Inserta el Pokémon en el ViewModel.
                        Toast.makeText(context, addedToFavoriteMsg, Toast.LENGTH_SHORT)
                            .show() // Muestra mensaje de confirmación.
                    }
                },
                modifier = Modifier.size(48.dp) // Tamaño del ícono de favorito.
            ) {
                // El icono de favorito cambia según si el Pokémon ya es favorito o no.
                Icon(
                    imageVector = if (isAlreadyFavorite) {
                        Icons.Default.Favorite // Icono de "favorito" si ya está en la lista.
                    } else {
                        Icons.Default.FavoriteBorder // Icono de "no favorito" si no está en la lista.
                    },
                    contentDescription = stringResource(R.string.favorito), // Descripción accesible para lectores de pantalla.
                    tint = if (isAlreadyFavorite) Color.Red else Color.Gray // Color del icono (rojo si es favorito, gris si no lo es).
                )
            }

            // Columna que contiene el nombre, tipo y habilidad del Pokémon.
            Column(
                modifier = Modifier
                    .weight(1f) // Ocupa el espacio restante disponible en la fila.
                    .padding(start = 16.dp) // Espaciado entre la imagen y el texto.
            ) {
                // Mostrar el nombre del Pokémon.
                StandardTextComp(
                    text = pokemon.name,
                    style = MaterialTheme.typography.headlineSmall // Estilo del texto del nombre.
                )
                Spacer(modifier = Modifier.height(4.dp)) // Espaciado entre los elementos de texto.
                // Mostrar los tipos del Pokémon.
                StandardTextComp(
                    text = "Tipo: ${pokemon.types.joinToString(", ")}",
                    style = MaterialTheme.typography.titleSmall // Estilo del texto para tipos.
                )
                // Mostrar las habilidades del Pokémon.
                StandardTextComp(
                    text = "Habilidad: ${pokemon.abilities.joinToString(", ")}",
                    style = MaterialTheme.typography.titleSmall // Estilo del texto para habilidades.
                )
            }

            // Imagen del Pokémon cargada de forma asíncrona (AsyncImage).
            AsyncImage(
                model = pokemon.imageUrl, // URL de la imagen.
                contentDescription = pokemon.name, // Descripción accesible para lectores de pantalla.
                modifier = Modifier
                    .size(120.dp), // Tamaño de la imagen.
                contentScale = ContentScale.Crop, // Escala de la imagen.
                error = painterResource(id = R.drawable.pokemonhub) // Imagen de error si falla la carga.
            )
        }
    }
}


@Composable
fun PokemonLandCard(
    pokemon: Pokemon, // Pokémon que se pasa como parámetro para mostrar sus datos.
    favListViewModel: PokemonFavListViewModel, // ViewModel para gestionar los favoritos de los Pokémon.
    onClick: () -> Unit // Función que se ejecuta cuando se hace clic en la tarjeta.
) {
    // Se obtiene el contexto y la vista actual para manipular la barra de estado (para versiones superiores a Lollipop).
    val context = LocalContext.current
    val view = LocalView.current
    val favUiState by favListViewModel.uiState.collectAsState() // Observamos el estado de los favoritos en el ViewModel.
    val isAlreadyFavorite =
        favUiState.favorites.any { it.name == pokemon.name } // Comprobamos si el Pokémon ya está en favoritos.
    val alreadyfavorite =
        stringResource(R.string.already) // Mensaje cuando el Pokémon ya es favorito.
    val addedtofavorite =
        stringResource(R.string.addfavorite) // Mensaje cuando el Pokémon es añadido a favoritos.

    // Manejo del color de la barra de estado en la actividad (solo en versiones superiores a Lollipop).
    if (!view.isInEditMode) {
        val activity = context as Activity
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Cambiar color de la barra de estado a color primario.
            window.statusBarColor = MaterialTheme.colorScheme.primary.toArgb()
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val insetsController = WindowInsetsControllerCompat(window, view)
            // Cambiar el color de la barra de estado dependiendo de la luminancia del color primario.
            insetsController.isAppearanceLightStatusBars =
                MaterialTheme.colorScheme.primary.luminance() > 0.5f
        }
    }

    // Tarjeta que contiene la información del Pokémon.
    Card(
        modifier = Modifier
            .fillMaxWidth() // La tarjeta ocupa todo el ancho disponible.
            .padding(8.dp) // Espaciado dentro de la tarjeta.
            .clickable { onClick() }, // Acción de clic en la tarjeta, ejecutando la función `onClick`.
        shape = MaterialTheme.shapes.medium, // Forma con bordes redondeados.
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer // Color de fondo de la tarjeta.
        )
    ) {
        // Fila (Row) que contiene los elementos: imagen, nombre y botón de favorito.
        Row(
            modifier = Modifier
                .fillMaxWidth() // La fila ocupa todo el ancho.
                .background(MaterialTheme.colorScheme.secondary), // Fondo de la fila.
            horizontalArrangement = Arrangement.Start, // Alinea los elementos a la izquierda.
            verticalAlignment = Alignment.CenterVertically // Centra verticalmente los elementos.
        ) {
            // Imagen del Pokémon cargada asíncronamente.
            AsyncImage(
                model = pokemon.imageUrl, // URL de la imagen del Pokémon.
                contentDescription = pokemon.name, // Descripción accesible para lectores de pantalla.
                modifier = Modifier
                    .fillMaxHeight() // La imagen llena la altura disponible.
                    .size(150.dp), // Tamaño de la imagen.
                contentScale = ContentScale.Crop, // Escala para ajustar la imagen dentro del cuadro.
                error = painterResource(id = R.drawable.pokemonhub) // Imagen por defecto si hay error.
            )

            // Contenedor de texto para el nombre del Pokémon.
            Box(
                modifier = Modifier
                    .weight(1f) // Toma el espacio disponible restante.
                    .padding(start = 16.dp), // Espaciado entre la imagen y el texto.
                contentAlignment = Alignment.CenterStart // Alineación del texto a la izquierda.
            ) {
                StandardTextComp(
                    text = pokemon.name, // Nombre del Pokémon.
                    style = MaterialTheme.typography.headlineMedium // Estilo del texto.
                )
            }

            // Botón para agregar a favoritos.
            IconButton(
                onClick = {
                    // Si el Pokémon ya está en favoritos, mostrar un mensaje.
                    if (isAlreadyFavorite) {
                        Toast.makeText(context, alreadyfavorite, Toast.LENGTH_SHORT).show()
                    } else {
                        // Si no está en favoritos, agregarlo a la lista de favoritos en el ViewModel.
                        val favoriteItem = PokeModel(
                            name = pokemon.name,
                            imageUrl = pokemon.imageUrl,
                            colorHex = "", // Aquí puedes agregar un color si es necesario.
                            types = pokemon.types,
                            abilities = pokemon.abilities,
                            stats = pokemon.stats
                        )
                        favListViewModel.insertFavourite(favoriteItem) // Añadir a favoritos.
                        Toast.makeText(context, addedtofavorite, Toast.LENGTH_SHORT)
                            .show() // Mostrar mensaje.
                    }
                },
            ) {
                // El icono de favorito cambia dependiendo de si el Pokémon ya está en la lista de favoritos.
                Icon(
                    imageVector = if (isAlreadyFavorite) {
                        Icons.Default.Favorite // Icono lleno si es favorito.
                    } else {
                        Icons.Default.FavoriteBorder // Icono vacío si no es favorito.
                    },
                    contentDescription = stringResource(R.string.favorito), // Descripción accesible para lectores de pantalla.
                    tint = if (isAlreadyFavorite) Color.Red else Color.Gray // Color del icono (rojo si es favorito, gris si no lo es).
                )
            }
        }
    }
}


@Composable
fun PokemonFavouriteCard(
    pokemon: PokeModel, // Información del Pokémon.
    onClick: () -> Unit, // Acción al hacer clic en la tarjeta.
    onClickClear: () -> Unit // Acción al hacer clic en el icono de eliminar.
) {
    val context = LocalContext.current // Obtiene el contexto actual de la aplicación.
    val view =
        LocalView.current // Obtiene la vista actual para manipular la barra de estado si es necesario.

    // Manejo del color de la barra de estado (solo para versiones superiores a Lollipop).
    if (!view.isInEditMode) {
        val activity = context as Activity
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Cambia el color de la barra de estado a color primario.
            window.statusBarColor = MaterialTheme.colorScheme.primary.toArgb()
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val insetsController = WindowInsetsControllerCompat(window, view)
            // Ajusta la apariencia de la barra de estado según la luminancia del color primario.
            insetsController.isAppearanceLightStatusBars =
                MaterialTheme.colorScheme.primary.luminance() > 0.5f
        }
    }

    // Tarjeta que contiene la información del Pokémon.
    Card(
        modifier = Modifier
            .fillMaxWidth() // La tarjeta ocupa todo el ancho.
            .padding(8.dp) // Espaciado dentro de la tarjeta.
            .fillMaxSize() // Ocupa todo el tamaño disponible.
            .clickable { onClick() }, // Acción al hacer clic en la tarjeta.
        shape = RoundedCornerShape(12.dp), // Bordes redondeados para la tarjeta.
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer // Color de fondo de la tarjeta.
        )
    ) {
        Row(
            Modifier.background(MaterialTheme.colorScheme.tertiaryContainer), // Fondo de la fila.
            verticalAlignment = Alignment.CenterVertically, // Alineación vertical.
            horizontalArrangement = Arrangement.Start // Alineación horizontal a la izquierda.
        ) {
            // Imagen del Pokémon cargada de manera asíncrona.
            AsyncImage(
                model = pokemon.imageUrl, // URL de la imagen.
                contentDescription = pokemon.name, // Descripción accesible para lectores de pantalla.
                modifier = Modifier
                    .fillMaxHeight() // Ocupa toda la altura disponible.
                    .size(150.dp), // Tamaño de la imagen.
                contentScale = ContentScale.Crop, // Ajuste de la imagen.
                error = painterResource(id = R.drawable.pokemonhub) // Imagen por defecto si hay error.
            )
            Column(
                modifier = Modifier
                    .weight(1f) // Ocupa el espacio restante.
                    .padding(start = 8.dp) // Espaciado a la izquierda.
            ) {
                StandardTextNull(pokemon.name) // Muestra el nombre del Pokémon.
            }
            // Icono de eliminar favorito.
            IconButton(
                onClick = { onClickClear() }, // Acción al hacer clic en el icono de eliminar.
                modifier = Modifier.size(38.dp) // Tamaño del icono.
            ) {
                Icon(
                    imageVector = Icons.Default.Delete, // Icono de eliminar.
                    modifier = Modifier.size(38.dp),
                    contentDescription = stringResource(R.string.icono_eliminar), // Descripción accesible.
                    tint = MaterialTheme.colorScheme.surface // Color del icono.
                )
            }
        }
    }
}


@Composable
fun PokemonFavouriteLandCard(
    pokemon: PokeModel, // Información del Pokémon.
    onClick: () -> Unit, // Acción al hacer clic en la tarjeta.
    onClickClear: () -> Unit // Acción al hacer clic en el ícono de eliminar.
) {
    val context = LocalContext.current // Obtiene el contexto actual de la aplicación.
    val view =
        LocalView.current // Obtiene la vista actual para manipular la barra de estado si es necesario.

    // Manejo del color de la barra de estado (solo para versiones superiores a Lollipop).
    if (!view.isInEditMode && context is Activity) {
        val activity = context as Activity
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Cambia el color de la barra de estado a color primario.
            window.statusBarColor = MaterialTheme.colorScheme.primary.toArgb()
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val insetsController = WindowInsetsControllerCompat(window, view)
            // Ajusta la apariencia de la barra de estado según la luminancia del color primario.
            insetsController.isAppearanceLightStatusBars =
                MaterialTheme.colorScheme.primary.luminance() > 0.5f
        }
    }

    val backgroundColor = try {
        // Verifica si el color hexadecimal no está vacío o nulo
        if (!pokemon.colorHex.isNullOrEmpty()) {
            // Intenta parsear el color, si es válido, se utiliza, si no, asigna un color predeterminado.
            Color(android.graphics.Color.parseColor(pokemon.colorHex))
        } else {
            // Si el color es nulo o vacío, se asigna un color predeterminado.
            Color.Gray
        }
    } catch (e: IllegalArgumentException) {
        // Si ocurre una excepción (color inválido), se usa un color predeterminado.
        Color.Gray
    }

    // Tarjeta que contiene la información del Pokémon con un fondo de color.
    Card(
        modifier = Modifier
            .fillMaxWidth() // La tarjeta ocupa todo el ancho.
            .padding(8.dp) // Espaciado dentro de la tarjeta.
            .height(150.dp) // Altura de la tarjeta.
            .clickable { onClick() }, // Acción al hacer clic en la tarjeta.
        shape = MaterialTheme.shapes.medium, // Bordes redondeados de la tarjeta.
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer // Color de fondo de la tarjeta.
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor), // Fondo con el color validado.
            horizontalArrangement = Arrangement.Start, // Alineación horizontal a la izquierda.
            verticalAlignment = Alignment.CenterVertically // Alineación vertical al centro.
        ) {
            // Imagen del Pokémon cargada de manera asíncrona.
            AsyncImage(
                model = pokemon.imageUrl, // URL de la imagen.
                contentDescription = pokemon.name, // Descripción accesible para lectores de pantalla.
                modifier = Modifier
                    .height(150.dp) // Altura de la imagen.
                    .width(150.dp), // Ancho de la imagen.
                contentScale = ContentScale.Crop, // Ajuste de la imagen.
                error = painterResource(id = R.drawable.pokemonhub) // Imagen por defecto si hay error.
            )

            // Contenedor para el nombre del Pokémon.
            Box(
                modifier = Modifier
                    .weight(1f) // Toma el espacio restante.
                    .padding(start = 16.dp), // Espaciado a la izquierda.
                contentAlignment = Alignment.CenterStart // Alineación del texto a la izquierda.
            ) {
                StandardTextNull(pokemon.name) // Muestra el nombre del Pokémon.
            }

            // Icono de eliminar favorito.
            IconButton(
                onClick = { onClickClear() }, // Acción al hacer clic en el icono de eliminar.
                modifier = Modifier.size(38.dp) // Tamaño del icono.
            ) {
                Icon(
                    imageVector = Icons.Default.Delete, // Icono de eliminar.
                    modifier = Modifier.size(38.dp),
                    contentDescription = stringResource(R.string.icono_eliminar), // Descripción accesible.
                    tint = MaterialTheme.colorScheme.surface // Color del icono.
                )
            }
        }
    }
}



@Composable
fun PokemonDetailCard(
    pokemon: Pokemon,  // Usamos ListModel aquí para acceder a la información del Pokémon.
    onFavoriteClick: () -> Unit, // Función para manejar la acción de agregar a favoritos.
    modifier: Modifier = Modifier, // Modificador opcional para personalizar la apariencia del Card.
    onBackClick: () -> Unit // Función para manejar el evento de retroceso (volver a la pantalla anterior).
) {
    val context = LocalContext.current // Obtiene el contexto de la aplicación.
    val view = LocalView.current // Obtiene la vista actual.

    // Manejo de la barra de estado (para versiones superiores a Lollipop)
    if (!view.isInEditMode) {
        val activity = context as Activity // Convierte el contexto en una actividad.
        val window = activity.window // Obtiene la ventana de la actividad.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Usamos colorHex de ListModel y lo convertimos a Color para modificar la barra de estado.
            window.statusBarColor =
                pokemon.colorFondo.toArgb() // Asigna el color de fondo del Pokémon a la barra de estado.
            WindowCompat.setDecorFitsSystemWindows(
                window,
                false
            ) // Ajusta el contenido a la barra de estado.
            val insetsController = WindowInsetsControllerCompat(window, view)
            // Establece la visibilidad de los íconos en la barra de estado según el color de fondo del Pokémon.
            insetsController.isAppearanceLightStatusBars = pokemon.colorFondo.luminance() > 0.5f
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()  // El Card ocupa todo el ancho disponible.
            .fillMaxHeight(), // El Card ocupa toda la altura disponible.
        shape = MaterialTheme.shapes.extraSmall, // Usamos un borde pequeño para el Card.
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer // El color de fondo del Card.
        )
    ) {
        // Barra superior con título y botón de retroceso
        BarraDetallesPokemon(stringResource(R.string.detalles_pokemon), pokemon, onBackClick)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(pokemon.colorFondo) // Usamos el color de fondo del Pokémon para el fondo de la columna.
                .padding(8.dp) // Padding alrededor del contenido.
        ) {
            // Imagen del Pokémon con un tamaño ajustado y un manejo de error si no se carga.
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier
                    .fillMaxWidth() // La imagen ocupa todo el ancho disponible.
                    .height(300.dp), // La imagen tiene una altura fija de 300dp.
                contentScale = ContentScale.Fit, // Ajusta la imagen para que encaje adecuadamente.
                error = painterResource(id = R.drawable.pokemonhub) // Imagen por defecto si no se carga la original.
            )

            Spacer(modifier = Modifier.height(8.dp)) // Espacio entre la imagen y el texto.

            // Nombre del Pokémon en un formato específico
            StandardTextDetail(stringResource(R.string.nombreDetail, pokemon.name))

            Spacer(modifier = Modifier.height(8.dp)) // Espacio adicional después del nombre.

            // Tipos del Pokémon, si están vacíos muestra un mensaje alternativo.
            if (pokemon.types.isEmpty()) {
                StandardTextDetailFav(stringResource(R.string.noTypes)) // Muestra un mensaje si no hay tipos.
            } else {
                StandardTextDetailFav(
                    stringResource(
                        R.string.typeDetail,
                        pokemon.types.joinToString(", ")
                    )
                ) // Muestra los tipos del Pokémon.
            }

            // Habilidades del Pokémon, similar a los tipos, si no hay habilidades se muestra un mensaje alternativo.
            if (pokemon.abilities.isEmpty()) {
                StandardTextDetailFav(stringResource(R.string.noAbilities)) // Muestra un mensaje si no hay habilidades.
            } else {
                StandardTextDetailFav(
                    stringResource(
                        R.string.abilityDetail,
                        pokemon.abilities.joinToString(", ")
                    )
                ) // Muestra las habilidades del Pokémon.
            }

            // Estadísticas del Pokémon, se maneja de la misma forma que los tipos y habilidades.
            if (pokemon.stats.isEmpty()) {
                StandardTextDetailFav(stringResource(R.string.noStats)) // Muestra un mensaje si no hay estadísticas.
            } else {
                StandardTextDetailFav(
                    stringResource(
                        R.string.statsDetail,
                        pokemon.stats.joinToString(", ")
                    )
                ) // Muestra las estadísticas del Pokémon.
            }

            Spacer(modifier = Modifier.height(8.dp)) // Espacio adicional después de las estadísticas.

            // Botón de agregar a favoritos
            Button(
                onClick = onFavoriteClick, // Llama a la función onFavoriteClick al hacer clic.
                modifier = Modifier
                    .fillMaxWidth() // El botón ocupa todo el ancho disponible.
                    .height(70.dp), // El botón tiene una altura fija de 70dp.
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary, // El color de fondo del botón.
                    contentColor = MaterialTheme.colorScheme.onPrimary // El color del texto en el botón.
                )
            ) {
                StandardTextDetail(stringResource(R.string.favorito)) // Muestra el texto de "Favorito" en el botón.
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
        shape = MaterialTheme.shapes.extraSmall,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        )
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
    favorite: PokeModel, // Usamos el modelo de datos PokeModel, que contiene la información del Pokémon favorito
    modifier: Modifier = Modifier, // Modificador opcional para personalizar el estilo del componente
    onBackClick: () -> Unit, // Función que se ejecuta cuando se hace clic en el botón de regresar
    profileViewModel: ProfileViewModel = viewModel(factory = ProfileViewModel.Factory), // ViewModel para manejar el estado del perfil de usuario
    pokemonFavListScreenViewModel: PokemonFavListViewModel = viewModel(), // ViewModel para manejar los comentarios del Pokémon favorito
) {
    // Obtiene el estado del usuario (nombre de usuario) desde el ViewModel
    val profileUiState by profileViewModel.uiState.collectAsState()
    val username = profileUiState.nombreUsuario

    // Obtiene los comentarios del Pokémon favorito desde el ViewModel
    val commentsFlow = pokemonFavListScreenViewModel.getCommentsForFavorite(favorite.name ?: "")
    val comments by commentsFlow.collectAsState(initial = emptyList()) // Lista de comentarios

    // Variables para manejar el estado del diálogo de comentarios
    var showDialog by remember { mutableStateOf(false) }
    var commentText by remember { mutableStateOf("") }

    // Obtiene el contexto y la vista para manipular la barra de estado
    val context = LocalContext.current
    val view = LocalView.current

    // Intentamos obtener el color de fondo desde el modelo de datos, si no es válido, usamos un color predeterminado
    val bgColor = try {
        Color(android.graphics.Color.parseColor(favorite.colorHex ?: "#FF000000"))
    } catch (e: Exception) {
        colorResource(R.color.purple_500)
    }
    val bgColorLuminance =
        bgColor // Guardamos la luminancia del color de fondo para determinar la apariencia de la barra de estado

    // Modificamos la barra de estado si la vista no está en modo edición
    if (!view.isInEditMode) {
        val activity = context as Activity
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = bgColor.toArgb() // Cambiamos el color de la barra de estado
            WindowCompat.setDecorFitsSystemWindows(
                window,
                false
            ) // Ajustamos el diseño del sistema para que no interfiera
            val insetsController = WindowInsetsControllerCompat(window, view)
            insetsController.isAppearanceLightStatusBars =
                bgColorLuminance.luminance() > 0.5f // Determinamos si la barra debe ser oscura o clara
        }
    }

    // Card que contiene toda la información del Pokémon
    Card(
        modifier = modifier
            .fillMaxWidth() // Hace que el card ocupe todo el ancho disponible
            .padding(8.dp), // Añade un margen alrededor del card
        shape = RoundedCornerShape(12.dp), // Redondea las esquinas del card
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer // Establece el color de fondo del card
        )
    ) {
        // Barra de detalles con el nombre del Pokémon y el botón de regresar
        BarraDetallesPokemonFavorito(stringResource(R.string.detail_fav), favorite, onBackClick)

        Column(
            modifier = Modifier
                .fillMaxWidth() // Hace que la columna ocupe todo el ancho disponible
                .background(bgColor) // Establece el color de fondo de la columna
                .padding(8.dp) // Añade un padding alrededor de los elementos dentro de la columna
        ) {
            // Mostrar la imagen del Pokémon
            AsyncImage(
                model = favorite.imageUrl, // URL de la imagen del Pokémon
                contentDescription = favorite.name, // Descripción de la imagen (nombre del Pokémon)
                modifier = Modifier
                    .height(300.dp)  // Altura fija para la imagen
                    .fillMaxWidth(), // La imagen ocupa todo el ancho disponible
                contentScale = ContentScale.Fit,  // Ajustamos la imagen para que no se recorte, se adapta al contenedor
                error = painterResource(id = R.drawable.pokemonhub) // Imagen por defecto si no se carga la original
            )

            Spacer(modifier = Modifier.height(16.dp)) // Espaciado entre la imagen y el siguiente elemento

            // Mostrar el nombre del Pokémon
            StandardText(
                stringResource(
                    R.string.nombreDetail,
                    favorite.name
                        ?: stringResource(R.string.noname) // Si no hay nombre, mostramos "noname"
                )
            )
            Spacer(modifier = Modifier.height(8.dp)) // Espaciado entre el nombre y los siguientes elementos

            // Mostrar las habilidades del Pokémon (si las tiene)
            if (favorite.abilities.isEmpty()) {
                PokemonDetailFavText(stringResource(R.string.noAbilities)) // Si no tiene habilidades, mostramos un mensaje
            } else {
                PokemonDetailFavText(
                    stringResource(
                        R.string.abilityDetail,
                        favorite.abilities.joinToString(", ") // Muestra las habilidades como una lista separada por comas
                    )
                )
            }

            // Mostrar los tipos del Pokémon (si los tiene)
            if (favorite.types.isEmpty()) {
                PokemonDetailFavText(stringResource(R.string.noTypes)) // Si no tiene tipos, mostramos un mensaje
            } else {
                PokemonDetailFavText(
                    stringResource(
                        R.string.typeDetail,
                        favorite.types.joinToString(", ")
                    )
                ) // Muestra los tipos
            }

            // Mostrar las estadísticas del Pokémon (si las tiene)
            if (favorite.stats.isEmpty()) {
                PokemonDetailFavText(stringResource(R.string.noStats)) // Si no tiene estadísticas, mostramos un mensaje
            } else {
                PokemonDetailFavText(
                    stringResource(
                        R.string.statsDetail,
                        favorite.stats.joinToString(", ")
                    )
                ) // Muestra las estadísticas
            }

            Spacer(modifier = Modifier.height(4.dp)) // Espaciado entre las estadísticas y los comentarios

            // Título de la sección de comentarios
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                StandardText(stringResource(R.string.listofcomment)) // Texto que indica la lista de comentarios
            }

            // Lista de comentarios
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Limita la altura de la lista de comentarios
                    .padding(end = 8.dp)
            ) {
                items(comments) { commentText -> // Recorre la lista de comentarios
                    CommentCard(commentText) // Muestra cada comentario
                }
            }

            // Botón para agregar un comentario
            FloatingActionButton(
                onClick = {
                    showDialog = true
                }, // Al hacer clic, muestra el diálogo para agregar un comentario
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.End), // El botón se coloca al final (a la derecha)
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Filled.Add, // Icono del botón (un signo de suma)
                    contentDescription = stringResource(R.string.add_comment), // Descripción del botón
                    tint = MaterialTheme.colorScheme.onPrimary // Color del icono
                )
            }

            // Diálogo para agregar un comentario
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showDialog = false
                    }, // Cierra el diálogo al hacer clic fuera de él
                    title = { Text(stringResource(R.string.add_comment)) }, // Título del diálogo
                    text = {
                        TextField(
                            value = commentText, // El valor del campo de texto es el comentario
                            onValueChange = {
                                commentText = it
                            }, // Actualiza el texto del comentario
                            label = { Text(stringResource(R.string.comment)) } // Etiqueta del campo de texto
                        )
                    },
                    confirmButton = {
                        Button(onClick = {
                            // Guarda el comentario en el ViewModel
                            pokemonFavListScreenViewModel.addCommentToFavorite(
                                favorite.name ?: "", // Usamos el nombre del Pokémon desde ListModel
                                username,
                                commentText
                            )
                            commentText = "" // Resetea el texto del comentario
                            showDialog = false // Cierra el diálogo
                        }) {
                            Text(stringResource(R.string.save)) // Botón de confirmación
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showDialog = false }) { // Botón para cancelar
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

