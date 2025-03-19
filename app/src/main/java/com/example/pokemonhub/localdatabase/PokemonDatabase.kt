package com.example.pokemonhub.localdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokemonhub.datamodel.Comment
import com.example.pokemonhub.datamodel.Convert
import com.example.pokemonhub.datamodel.PokeModel

// **Definición de la base de datos Room**
// - Contiene las entidades `PokeModel` y `Comment`
// - Usa `TypeConverters` para convertir tipos de datos no soportados directamente por Room

@Database(entities = [PokeModel::class, Comment::class], version = 13, exportSchema = false)
@TypeConverters(Convert::class)
abstract class PokemonDatabase : RoomDatabase() {

    // **DAO para gestionar la lista de Pokémon favoritos**
    abstract fun pokeDAO(): PokeDAO

    // **DAO para gestionar los comentarios**
    abstract fun commentDAO(): CommentDAO

    companion object {
        // **Referencia única a la instancia de la base de datos**
        @Volatile
        private var Instance: PokemonDatabase? = null

        // **Método para obtener la base de datos**
        // - Si ya existe, se devuelve la misma instancia
        // - Si no, se crea una nueva base de datos de manera sincronizada (Thread-safe)
        fun getDatabase(context: Context): PokemonDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, PokemonDatabase::class.java, "pokemon_database") // **Nombre del archivo de la base de datos**
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
