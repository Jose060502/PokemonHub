package com.example.pokemonhub.localdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemonhub.datamodel.Comment
import kotlinx.coroutines.flow.Flow

// **DAO (Data Access Object) para la tabla "comments"**
// Define las operaciones de acceso a los comentarios en la base de datos local

@Dao
interface CommentDAO {

    // **Inserta un comentario en la base de datos**
    // - Usa OnConflictStrategy.ABORT para evitar duplicados y generar un error si ya existe
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertComment(comment: Comment)

    // **Obtiene todos los comentarios asociados a un Pokémon favorito**
    // - Usa Flow para obtener actualizaciones en tiempo real de la base de datos
    @Query("SELECT * FROM comments WHERE favoriteName = :favoriteName")
    fun getCommentsByFavoriteName(favoriteName: String): Flow<List<Comment>>
}