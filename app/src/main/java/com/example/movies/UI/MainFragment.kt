package com.example.movies.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.Adapters.MoviesAdapter
import com.example.movies.R
import com.example.movies.ViewModels.MainViewModel
import com.example.moviescourse.Model.Movie.Movie

class MainFragment : Fragment() {

    private lateinit var recyclerViewMain : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_main, container, false)
        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        val recyclerViewMain = view.findViewById<RecyclerView>(R.id.recyclerViewMain)
        val moviesAdapter = MoviesAdapter()
        recyclerViewMain.adapter = moviesAdapter

        mainViewModel.getMovies().observe(
            viewLifecycleOwner, Observer {
                moviesAdapter.setMovies(it)
            })

        mainViewModel.getIsLoading().observe(
            viewLifecycleOwner, object : Observer<Boolean>{
            override fun onChanged(isLoading: Boolean?) {
                if(isLoading == true)
                    progressBar.visibility = View.VISIBLE
                else
                    progressBar.visibility = View.GONE
            }
        })

        moviesAdapter.setOnReachEndListener(
            object : MoviesAdapter.OnReachEndListener{
                override fun onReachEnd() {
                    mainViewModel.loadMovies()
                }
            })

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