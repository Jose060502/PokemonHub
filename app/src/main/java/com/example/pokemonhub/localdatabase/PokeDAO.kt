package com.example.pokemonhub.localdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemonhub.datamodel.PokeModel
import kotlinx.coroutines.flow.Flow

// **DAO (Data Access Object) para la tabla "favoritelist"**
// Define las operaciones de acceso a los Pokémon favoritos en la base de datos local

@Dao
interface PokeDAO {

    // **Inserta un Pokémon en la lista de favoritos**
    // - Usa OnConflictStrategy.ABORT para evitar duplicados y generar un error si ya existe
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(pokemon: PokeModel)

    // **Elimina un Pokémon de la lista de favoritos**
    @Delete
    suspend fun delete(pokemon: PokeModel)

    // **Obtiene la lista completa de Pokémon favoritos**
    // - Usa Flow para recibir actualizaciones en tiempo real cuando haya cambios en la base de datos
    @Query("SELECT * FROM pokefavoritelist")
    fun getAllPokemons(): Flow<List<PokeModel>>
}
