package com.example.pokemonhub.repository

import com.example.pokemonhub.datamodel.Comment
import com.example.pokemonhub.localdatabase.CommentDAO
import kotlinx.coroutines.flow.Flow

class CommentRepository(private val commentDAO: CommentDAO) {

    suspend fun insertComment(comment: Comment) {
        commentDAO.insertComment(comment)
    }

    fun getCommentsByFavoriteId(favoriteName: String): Flow<List<Comment>> {
        return commentDAO.getCommentsByFavoriteName(favoriteName)
    }
}