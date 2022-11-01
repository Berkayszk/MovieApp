package com.example.movieapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.adapter.MovieFavRecyclerAdapter
import com.example.movieapp.databinding.FragmentFavmoviesBinding
import com.example.movieapp.databinding.FragmentSearchMovieBinding
import com.example.movieapp.viewmodel.MovieViewModel
import javax.inject.Inject

class MovieFavFragment @Inject constructor(
    val movieRecyclerAdapter : MovieFavRecyclerAdapter
) : Fragment(R.layout.fragment_favmovies) {

    private var fragmentBinding : FragmentFavmoviesBinding? =null
    lateinit var viewModel : MovieViewModel

    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            val selectedMovie = movieRecyclerAdapter.movies[layoutPosition]
            viewModel.deleteMovie(selectedMovie)
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val binding = FragmentFavmoviesBinding.bind(view)
        fragmentBinding = binding
        subscribeToObservers()
        binding.recyclerViewFavMovie.adapter = movieRecyclerAdapter
        binding.recyclerViewFavMovie.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.recyclerViewFavMovie)
        viewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)




    }
    private fun subscribeToObservers() {
     viewModel.movieList.observe(viewLifecycleOwner, Observer{
         movieRecyclerAdapter.movies = it
     })
    }
}