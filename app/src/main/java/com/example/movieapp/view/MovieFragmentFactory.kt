package com.example.movieapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class MovieFragmentFactory  @Inject constructor(
    private val glide : RequestManager
): FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            MovieDetailsFragment::class.java.name -> MovieDetailsFragment(glide)
            else -> super.instantiate(classLoader, className)
        }

    }
}