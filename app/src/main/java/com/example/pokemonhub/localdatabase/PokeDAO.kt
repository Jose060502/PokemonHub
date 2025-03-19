package com.example.pokemonhub.localdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemonhub.datamodel.PokeModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PokeDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(pokemon: PokeModel)

    @Delete
    suspend fun delete(pokemon: PokeModel)

    @Query("SELECT * FROM favoritelist")
    fun getAllPokemons(): Flow<List<PokeModel>>
}