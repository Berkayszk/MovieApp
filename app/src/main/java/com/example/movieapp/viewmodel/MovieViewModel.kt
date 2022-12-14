package com.example.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.MovieResponse
import com.example.movieapp.repo.MovieRepositoryInterface
import com.example.movieapp.roomdb.Movie
import com.example.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
//movie viewModel =  fav fragment
class MovieViewModel @Inject constructor(
    private val repository : MovieRepositoryInterface
) : ViewModel() {

    //Movie Fav Fragment
    val movieList = repository.getMovie()

    //Movie Api Fragment
    private val images = MutableLiveData<Resource<com.example.movieapp.model.MovieResponse>>()
    val imageList : LiveData<Resource<com.example.movieapp.model.MovieResponse>>
    get() = images

    private val selectedImage = MutableLiveData<String>()
    val selectedImageUrl : LiveData<String>
    get() = selectedImage

    //Movie Details Fragment

    private var insertMovieMsg = MutableLiveData<Resource<Movie>>()
    val insertMovieMessage : LiveData<Resource<Movie>>
    get() = insertMovieMsg

    fun resetInsertMovieMsg(){
        insertMovieMsg = MutableLiveData<Resource<Movie>>()
    }
    //Repo

    fun setSelectedImage(url:String){
        selectedImage.postValue(url)
    }
    fun deleteMovie(movie : Movie) = viewModelScope.launch{
        repository.deleteMovie(movie)
    }
    fun insertMovie(movie:Movie) = viewModelScope.launch {
        repository.insertMovie(movie)
    }

    fun searchForMovie(searchString : String){
        if(searchString.isEmpty()){
            return
        }
        images.value
        viewModelScope.launch {
            val response = repository.searchMovie(searchString)
            images.value = response
        }
    }

}