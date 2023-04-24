package com.example.movies.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.moviescourse.Model.Movie.Movie

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private var movies = ArrayList<Movie>()
    private lateinit var onReachEndListener: OnReachEndListener
    private lateinit var onItemClickListener: OnItemClickListener

    fun setMovies(movies : List<Movie>){
        this.movies = movies as ArrayList<Movie>
        notifyDataSetChanged()
    }

    fun setOnReachEndListener(onReachEndListener: OnReachEndListener){
        this.onReachEndListener = onReachEndListener
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)

        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies.get(position)
        Glide.with(holder.itemView).load(movie.poster.url).into(holder.imageViewPoster)

        if(position >= movies.size - 10)
            onReachEndListener.onReachEnd()

        holder.itemView.setOnClickListener{
            onItemClickListener.onItemClick(movie)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageViewPoster = itemView.findViewById<ImageView>(R.id.imageViewPoster)

    }

    interface OnReachEndListener{
        fun onReachEnd()
    }

    interface OnItemClickListener{
        fun onItemClick(movie : Movie)
    }
}