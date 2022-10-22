package com.example.movieapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)
    @Delete
    suspend fun deleteMovie(movie: Movie)
    @Query("SELECT * FROM movies")
    fun observeMovies(): LiveData<List<Movie>>


}