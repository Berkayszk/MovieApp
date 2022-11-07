package com.example.movieapp.view

import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.RequestManager
import com.example.movieapp.R
import com.example.movieapp.adapter.ApiFragmentRecyclerAdapter
import com.example.movieapp.databinding.FragmentMovieApiBinding
import com.example.movieapp.roomdb.Movie
import com.example.movieapp.util.Status
import com.example.movieapp.viewmodel.MovieViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieApiFragment @Inject constructor(
 private val apiRecyclerAdapter : ApiFragmentRecyclerAdapter
): Fragment(R.layout.fragment_movie_api) {

    lateinit var viewModel: MovieViewModel
    private var fragmentBinding : FragmentMovieApiBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovieApiBinding.bind(view)
        fragmentBinding = binding
        viewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
        subscribeToObservers()
        binding.movieRecyclerView.adapter =  apiRecyclerAdapter
        //binding.movieRecyclerView.layoutManager = GridLayoutManager(requireContext(),3)
        apiRecyclerAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            viewModel.setSelectedImage(it)
        }

        var job: Job? = null
        binding.searchText.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it?.let {
                    if (it.toString().isNotEmpty()){
                        viewModel.searchForMovie(it.toString())
                    }
                }
            }
        }



    }
    fun subscribeToObservers(){
        viewModel.imageList.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.ERROR ->{
                    Toast.makeText(requireContext(),it.message ?:"Error", Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS ->{
                    val urls = it.data?.hits?.map { movieResult ->
                        movieResult.genre
                        movieResult.year
                        movieResult.poster
                        movieResult.title
                    }
                    apiRecyclerAdapter.movies = (urls ?: listOf()) as List<Movie>


                }
                Status.LOADING ->{
                }
                }
        })

    }
}