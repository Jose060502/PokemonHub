package com.example.pokemonhub.localdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemonhub.datamodel.Comment
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertComment(comment: Comment)


    @Query("SELECT * FROM comments WHERE favoriteName = :favoriteName")
    fun getCommentsByFavoriteName(favoriteName: String): Flow<List<Comment>>
}