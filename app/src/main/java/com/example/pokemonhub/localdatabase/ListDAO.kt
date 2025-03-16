package com.example.pokemonhub.localdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemonhub.datamodel.ListModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ListDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(pokemon: ListModel)

    @Delete
    suspend fun delete(pokemon: ListModel)

    @Query("SELECT * FROM favoritelist")
    fun getAllPokemons(): Flow<List<ListModel>>
}