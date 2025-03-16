package com.example.pokemonhub.datamodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "userName")
    val userName: String,
    @ColumnInfo(name = "favoriteName")
    val favoriteName: String?,
    @ColumnInfo(name = "commentText")
    val commentText: String
)