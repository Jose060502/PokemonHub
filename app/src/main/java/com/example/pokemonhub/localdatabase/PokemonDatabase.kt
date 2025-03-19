package com.example.pokemonhub.localdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokemonhub.datamodel.Comment
import com.example.pokemonhub.datamodel.Convert
import com.example.pokemonhub.datamodel.PokeModel

@Database(entities = [PokeModel::class,Comment::class], version = 12, exportSchema = false)
@TypeConverters(Convert::class)
abstract class PokemonDatabase : RoomDatabase(){
    abstract fun ListDAO() : PokeDAO
    abstract fun commentDAO(): CommentDAO

    companion object {
        @Volatile
        private var Instance: PokemonDatabase? = null
        fun getDatabase(context: Context): PokemonDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context,PokemonDatabase::class.java, "pokemon_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}