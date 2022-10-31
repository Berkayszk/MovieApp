package com.example.movieapp.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    //burasi moiveleri göstereceğim kısımın ayarları da olabilir
    var movieName : String,
    var subject : String,
    var director : String,
    var year : Int,
    var genre : String,
    var imageUrl : String,
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
)
