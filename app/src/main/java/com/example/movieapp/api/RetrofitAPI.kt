package com.example.movieapp.api

import com.example.movieapp.model.MovieResponse
import com.example.movieapp.roomdb.Movie
import com.example.movieapp.util.Util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {
    @GET("?type=movie")
    fun searchMovie(
        @Query("apikey") apikey: String = API_KEY,
        @Query("s") searchKey: String,
        @Query("page") page: String
    ): Response<MovieResponse>
}