package com.example.movieapp.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentFavmoviesBinding
import com.example.movieapp.databinding.FragmentMovieDetailsBinding
import com.example.movieapp.util.Status
import com.example.movieapp.viewmodel.MovieViewModel
import javax.inject.Inject

class MovieDetailsFragment @Inject constructor(
    val glide : RequestManager
) : Fragment(R.layout.fragment_movie_details) {

    lateinit var viewModel : MovieViewModel
    private var fragmentBinding : FragmentMovieDetailsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
        val binding = FragmentMovieDetailsBinding.bind(view)
        fragmentBinding = binding
        viewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        binding.favButton.setOnClickListener {

        }

    }
    private fun subscribeToObservers(){
        viewModel.selectedImageUrl.observe(viewLifecycleOwner, Observer { url ->
            fragmentBinding?.let { binding ->
                glide.load(url).into(binding.imageView)
                viewModel.insertMovieMessage.observe(viewLifecycleOwner, Observer {
                    when(it.status){
                        Status.ERROR ->{
                            Toast.makeText(requireContext(),it.message ?:"Error", Toast.LENGTH_SHORT).show()
                        }
                        Status.SUCCESS ->{
                            Toast.makeText(requireContext(), "Succes", Toast.LENGTH_SHORT).show()
                            findNavController()
                            viewModel.resetInsertMovieMsg() //reset message type
                        }
                        Status.LOADING ->{

                        }
                    }
                })
            }
        })

    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }
}