package com.example.movieapp.api

import com.example.movieapp.model.MovieResponse
import com.example.movieapp.roomdb.Movie
import com.example.movieapp.util.Util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {
    @GET("/")
    fun searchMovie(
        @Query("s") s: String,
        @Query("page") p: Int,
        @Query("api") apiKey: String = API_KEY
    ): Response<MovieResponse>
}