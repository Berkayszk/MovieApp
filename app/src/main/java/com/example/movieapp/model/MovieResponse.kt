package com.example.movieapp.model

data class MovieResponse (
    val Search : List<MovieResult>,
    val totalResults : String,
    val Response: String
)

