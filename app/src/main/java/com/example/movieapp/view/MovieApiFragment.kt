package com.example.movieapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.RequestManager
import com.example.movieapp.R
import com.example.movieapp.adapter.ApiFragmentRecyclerAdapter
import javax.inject.Inject

class MovieApiFragment @Inject constructor(
 val apiFragmentAdapter : ApiFragmentRecyclerAdapter
): Fragment(R.layout.fragment_movie_api) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}