package com.example.pokemonhub.ui.screens.favouritelist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pokemonhub.datamodel.Comment
import com.example.pokemonhub.datamodel.ListModel
import com.example.pokemonhub.pokemonRelease.PokedexReleaseApplication
import com.example.pokemonhub.repository.CommentRepository
import com.example.pokemonhub.repository.FavoriteListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PokemonFavListViewModel(
    private val listRepository: FavoriteListRepository,
    private val commentRepository: CommentRepository
): ViewModel() {
    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PokedexReleaseApplication)
                PokemonFavListViewModel(
                    application.listRepository,
                    application.commentRepository
                )
            }
        }
    }

    private val _uiState = MutableStateFlow(PokemonFavouriteUiState())
    val uiState: StateFlow<PokemonFavouriteUiState> = _uiState.asStateFlow()
    init {
        recoverFavourites()
    }

    private fun recoverFavourites(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            listRepository.getAllPokemons.catch {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessageFAV = ErrorMessageFAV.ERROR_LOADING_FAVLIST
                )
            }
                .collect{
                    favList ->
                    _uiState.value = PokemonFavouriteUiState(
                        favorites = favList,
                        isLoading = false,
                        errorMessageFAV = null
                    )
                }
        }
    }
    
    fun insertFavourite(item: ListModel){
        viewModelScope.launch {
            try {
                Log.d("PokemonFavListViewModel", "Insertando favorito: $item")
                listRepository.insert(item)
            } catch (e : Exception){
                _uiState.value = _uiState.value.copy(
                    errorMessageFAV = ErrorMessageFAV.ERROR_INSERTING_FAV
                )
            }
        }
    }

    fun deleteFavourite(item: ListModel){
        viewModelScope.launch {
            try {
                listRepository.delete(item)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessageFAV = ErrorMessageFAV.ERROR_DELETING_FAV
                )
            }
        }
    }

    fun addCommentToFavorite(favoriteName: String?, userName: String, commentText: String) {
        viewModelScope.launch {
            try {
                val newComment = Comment(
                    favoriteName = favoriteName,
                    userName = userName,
                    commentText = commentText
                )
                commentRepository.insertComment(newComment)
            } catch (e: Exception) {
            }
        }
    }
    fun getCommentsForFavorite(favoriteName: String): Flow<List<Comment>> {
        return commentRepository.getCommentsByFavoriteId(favoriteName)
    }

}