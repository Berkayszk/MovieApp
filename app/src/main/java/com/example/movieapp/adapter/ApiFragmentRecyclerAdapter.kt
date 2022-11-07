package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.movieapp.R
import com.example.movieapp.roomdb.Movie
import javax.inject.Inject

class ApiFragmentRecyclerAdapter @Inject constructor(
    val glide : RequestManager
) : RecyclerView.Adapter<ApiFragmentRecyclerAdapter.MovieApiViewHolder>() {
    class MovieApiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    //push Url
    private var onItemClickListener : ((String) -> Unit) ? = null
    private val diffUtil = object : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)

    var movies : List<Movie>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieApiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_row,parent,false)
        return MovieApiViewHolder(view)
    }

    //doesn't matter
    fun setOnItemClickListener(listener : (String) -> Unit){
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: MovieApiViewHolder, position: Int) {
        /*
        val imageView = holder.itemView.findViewById<ImageView>(R.id.movieRowImageView)
        val nameText = holder.itemView.findViewById<TextView>(R.id.movieRowNameText)
        val yearText = holder.itemView.findViewById<TextView>(R.id.movieRowYearText)
        val genreText = holder.itemView.findViewById<TextView>(R.id.movieRowGenreText)
        val movie = movieApi[position]
        holder.itemView.apply {

        }

        val url = movieApi[position]
        holder.itemView.apply {
            glide.load(url).into(imageView)
            setOnClickListener {
                onItemClickListener?.let {
                    it(url)
                }
            }
        }
         */
        val imageView = holder.itemView.findViewById<ImageView>(R.id.movieRowImageView)
        val nameText = holder.itemView.findViewById<TextView>(R.id.movieRowNameText)
        val yearText = holder.itemView.findViewById<TextView>(R.id.movieRowYearText)
        val genreText = holder.itemView.findViewById<TextView>(R.id.movieRowGenreText)

        val movie = movies[position]
        holder.itemView.apply {
            nameText.text = "${movie.movieName}"
            yearText.text = "${movie.year}"
            genreText.text = "${movie.genre}"
            glide.load(movie.imageUrl).into(imageView)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}