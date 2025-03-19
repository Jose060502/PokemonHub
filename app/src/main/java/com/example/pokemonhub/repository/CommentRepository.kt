package com.example.pokemonhub.repository

import com.example.pokemonhub.datamodel.Comment
import com.example.pokemonhub.localdatabase.CommentDAO
import kotlinx.coroutines.flow.Flow

// **Repositorio para la gestión de comentarios** (Capa de datos en MVVM)
// - Actúa como intermediario entre la base de datos (Room) y la capa de dominio.
class CommentRepository(private val commentDAO: CommentDAO) {

    // **Inserta un comentario en la base de datos**
    // - Se ejecuta en una corrutina (suspend) para no bloquear el hilo principal.
    suspend fun insertComment(comment: Comment) {
        commentDAO.insertComment(comment)
    }

    // **Obtiene los comentarios asociados a un Pokémon favorito**
    // - Retorna un `Flow<List<Comment>>`, lo que permite recibir actualizaciones en tiempo real.
    fun getCommentsByFavoriteId(favoriteName: String): Flow<List<Comment>> {
        return commentDAO.getCommentsByFavoriteName(favoriteName)
    }
}