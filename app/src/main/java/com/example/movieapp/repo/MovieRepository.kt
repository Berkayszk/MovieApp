package com.example.movieapp.repo

import androidx.lifecycle.LiveData
import com.example.movieapp.api.RetrofitAPI
import com.example.movieapp.model.MovieResponse
import com.example.movieapp.roomdb.Movie
import com.example.movieapp.roomdb.MovieDao
import com.example.movieapp.util.Resource
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val retrofitApi : RetrofitAPI
): MovieRepositoryInterface {
    override suspend fun insertMovie(movie: Movie) {
        movieDao.insertMovie(movie)
    }

    override suspend fun deleteMovie(movie: Movie) {
        movieDao.deleteMovie(movie)
    }

    override fun getMovie(): LiveData<List<Movie>> {
        return movieDao.observeMovies()
    }

    override suspend fun searchMovie(movieString: String): Resource<MovieResponse> {
        return try {
            val response = retrofitApi.searchMovie(movieString)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error!!",null)
            } else {
                Resource.error("Error",null)
            }
        }
        catch (e:Exception){
            Resource.error("No Data!",null)
        }
    }
}