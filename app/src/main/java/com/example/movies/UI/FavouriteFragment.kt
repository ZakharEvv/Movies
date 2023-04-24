package com.example.movies.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.Adapters.MoviesAdapter
import com.example.movies.R
import com.example.movies.ViewModels.FavouriteViewModel
import com.example.moviescourse.Model.Movie.Movie


class FavouriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)
        val favouriteViewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewFavourite)
        val moviesAdapter = MoviesAdapter()
        recyclerView.adapter = moviesAdapter

        favouriteViewModel.getMovies().observe(
            viewLifecycleOwner, Observer {
                moviesAdapter.setMovies(it)
            }
        )

        moviesAdapter.setOnReachEndListener(
            object : MoviesAdapter.OnReachEndListener{ override fun onReachEnd() {} }
        )

        moviesAdapter.setOnItemClickListener(
            object : MoviesAdapter.OnItemClickListener{
                override fun onItemClick(movie: Movie) {
                    val bundle = Bundle()
                    bundle.putSerializable("movie", movie)
                    navController.navigate(R.id.detailFragment, bundle)
                }
            }
        )
        return view
    }
}