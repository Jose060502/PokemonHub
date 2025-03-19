package com.example.pokemonhub.datamodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// **Entidad Comment para la base de datos en Room**
// Representa un comentario almacenado en la base de datos local de la aplicación

@Entity(tableName = "comments") // Define la tabla "comments" en la base de datos
data class Comment(
    @PrimaryKey(autoGenerate = true) // La clave primaria se genera automáticamente
    val id: Int = 0,

    @ColumnInfo(name = "userName") // Columna para el nombre del usuario que hace el comentario
    val userName: String,

    @ColumnInfo(name = "favoriteName") // Columna opcional (puede ser null) para el nombre favorito del Pokémon o elemento comentado
    val favoriteName: String?,

    @ColumnInfo(name = "commentText") // Columna donde se almacena el contenido del comentario
    val commentText: String
)