package com.example.movieapp.api

import com.example.movieapp.model.MovieResponse
import com.example.movieapp.util.Util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {
    @GET("?apikey=${API_KEY}")
    suspend fun searchMovie(
        @Query("s") searchQuery : String,
        //@Query("t") searchTitle : String --title repo
    ) : Response<MovieResponse>
}