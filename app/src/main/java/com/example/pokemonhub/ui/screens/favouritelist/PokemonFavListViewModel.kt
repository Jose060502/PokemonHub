package com.example.pokemonhub.ui.screens.favouritelist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pokemonhub.datamodel.Comment
import com.example.pokemonhub.datamodel.PokeModel
import com.example.pokemonhub.pokemonRelease.PokedexReleaseApplication
import com.example.pokemonhub.repository.CommentRepository
import com.example.pokemonhub.repository.FavoritePokeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

// ViewModel encargado de gestionar el estado y las operaciones relacionadas con los Pokémon favoritos
class PokemonFavListViewModel(
    private val listRepository: FavoritePokeRepository,  // Repositorio para manejar los Pokémon favoritos
    private val commentRepository: CommentRepository     // Repositorio para manejar los comentarios
): ViewModel() {

    // Factory para crear una instancia del ViewModel con sus dependencias.
    companion object{
        // Factory que inicializa el ViewModel y le proporciona las dependencias necesarias.
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PokedexReleaseApplication)  // Obtiene la instancia de la aplicación
                PokemonFavListViewModel(
                    application.listRepository,        // Proveedor del repositorio de Pokémon favoritos
                    application.commentRepository      // Proveedor del repositorio de comentarios
                )
            }
        }
    }

    // MutableStateFlow que representa el estado mutable de la UI
    private val _uiState = MutableStateFlow(PokemonFavouriteUiState())

    // StateFlow que representa el estado inmutable de la UI, disponible para ser observado
    val uiState: StateFlow<PokemonFavouriteUiState> = _uiState.asStateFlow()

    // Inicialización: se recuperan los Pokémon favoritos al crear el ViewModel
    init {
        recoverFavourites()
    }

    // Función para recuperar la lista de Pokémon favoritos desde el repositorio
    private fun recoverFavourites() {
        viewModelScope.launch {  // Ejecuta la operación de manera asincrónica en el scope del ViewModel
            _uiState.value = _uiState.value.copy(isLoading = true)  // Marca que los datos están siendo cargados
            listRepository.getAllPokemons.catch {
                // En caso de error al obtener los Pokémon favoritos
                _uiState.value = _uiState.value.copy(
                    isLoading = false,  // Detiene el estado de carga
                    errorMessageFAV = ErrorMessageFAV.ERROR_LOADING_FAVLIST  // Establece el error específico
                )
            }
                .collect { favList ->  // Al recibir la lista de favoritos
                    // Actualiza el estado con los Pokémon favoritos y termina la carga
                    _uiState.value = PokemonFavouriteUiState(
                        favorites = favList,
                        isLoading = false,
                        errorMessageFAV = null  // Sin errores
                    )
                }
        }
    }

    // Función para insertar un nuevo Pokémon en los favoritos
    fun insertFavourite(item: PokeModel) {
        viewModelScope.launch {
            try {
                Log.d("PokemonFavListViewModel", "Insertando favorito: $item")  // Registra la acción en los logs
                listRepository.insert(item)  // Inserta el Pokémon en el repositorio
            } catch (e: Exception) {
                // Si ocurre un error, actualiza el estado con un mensaje de error
                _uiState.value = _uiState.value.copy(
                    errorMessageFAV = ErrorMessageFAV.ERROR_INSERTING_FAV
                )
            }
        }
    }

    // Función para eliminar un Pokémon de los favoritos
    fun deleteFavourite(item: PokeModel) {
        viewModelScope.launch {
            try {
                listRepository.delete(item)  // Elimina el Pokémon del repositorio
            } catch (e: Exception) {
                // Si ocurre un error, actualiza el estado con un mensaje de error
                _uiState.value = _uiState.value.copy(
                    errorMessageFAV = ErrorMessageFAV.ERROR_DELETING_FAV
                )
            }
        }
    }

    // Función para agregar un comentario a un Pokémon favorito
    fun addCommentToFavorite(favoriteName: String?, userName: String, commentText: String) {
        viewModelScope.launch {
            try {
                // Crea un nuevo comentario con los datos proporcionados
                val newComment = Comment(
                    favoriteName = favoriteName,
                    userName = userName,
                    commentText = commentText
                )
                commentRepository.insertComment(newComment)  // Inserta el comentario en el repositorio
            } catch (e: Exception) {
                // No se maneja el error aquí, pero se podría agregar un manejo de excepciones si fuera necesario
            }
        }
    }

    // Función para obtener los comentarios asociados a un Pokémon favorito
    fun getCommentsForFavorite(favoriteName: String): Flow<List<Comment>> {
        return commentRepository.getCommentsByFavoriteId(favoriteName)  // Devuelve los comentarios del repositorio
    }
}
