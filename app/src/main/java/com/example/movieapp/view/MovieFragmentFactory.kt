package com.example.movieapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.example.movieapp.adapter.ApiFragmentRecyclerAdapter
import com.example.movieapp.adapter.MovieFavRecyclerAdapter
import javax.inject.Inject

class MovieFragmentFactory  @Inject constructor(
    private val movieRecylcerAdapter : MovieFavRecyclerAdapter,
    private val glide : RequestManager,
    private val apiFragmentRecyclerAdapter: ApiFragmentRecyclerAdapter
): FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            MovieApiFragment::class.java.name -> MovieApiFragment(apiFragmentRecyclerAdapter)
            MovieFavFragment::class.java.name -> MovieFavFragment(movieRecylcerAdapter)
            MovieDetailsFragment::class.java.name -> MovieDetailsFragment(glide)
            else -> super.instantiate(classLoader, className)
        }

    }
}