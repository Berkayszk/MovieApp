package com.example.movieapp.repo

import androidx.lifecycle.LiveData
import com.example.movieapp.roomdb.Movie
import com.example.movieapp.util.Resource

interface MovieRepositoryInterface {
    suspend fun insertMovie(movie : Movie)

    suspend fun deleteMovie(movie : Movie)

    fun getMovie() : LiveData<List<Movie>>
    suspend fun searchMovie(movieString : String) : Resource<com.example.movieapp.model.MovieResponse>
}