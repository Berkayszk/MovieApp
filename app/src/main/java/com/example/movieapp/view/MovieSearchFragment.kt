package com.example.movieapp.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSearchMovieBinding

class MovieSearchFragment : Fragment(R.layout.fragment_search_movie) {

    private var fragmentBinding : FragmentSearchMovieBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSearchMovieBinding.bind(view)
        fragmentBinding = binding

        binding.searchMovieApiText.setOnClickListener {
            findNavController().navigate(MovieSearchFragmentDirections.actionMovieSearchFragmentToMovieApiFragment())
        }
        binding.fab.setOnClickListener {
            findNavController().navigate(MovieSearchFragmentDirections.actionMovieSearchFragmentToMovieFavFragment())
        }
        binding.searchButton.setOnClickListener {
            findNavController().navigate(MovieSearchFragmentDirections.actionMovieSearchFragmentToMovieApiFragment())
        }
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }
}