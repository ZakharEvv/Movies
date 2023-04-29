package com.example.movies.ui

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.ui.Adapters.ReviewsAdapter
import com.example.movies.R
import com.example.movies.viewmodels.DetailViewModel
import com.example.moviescourse.Model.Movie.Movie
import com.example.moviescourse.Model.Review.Review


class DetailFragment : Fragment() {

    private lateinit var imageViewDetail : ImageView
    private lateinit var tvName : TextView
    private lateinit var tvYear : TextView
    private lateinit var tvDescription : TextView
    private lateinit var btnAddToFavourite : ImageView
    private lateinit var btnTrailer : CardView
    private lateinit var favouriteUnchecked : Drawable
    private lateinit var favouriteChecked : Drawable
    private lateinit var recyclerViewReviews : RecyclerView
    private lateinit var reviewsLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val movie : Movie = arguments?.getSerializable("movie") as Movie
        initViews(view)

        val detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        Glide.with(this).load(movie.poster.url).into(imageViewDetail)
        tvName.text = movie.name
        tvYear.text = movie.year.toString()
        tvDescription.text = movie.description

        detailViewModel.getFavouriteMovie(movie.id).observe(
            viewLifecycleOwner,
            Observer {
                if (it == null) {
                    btnAddToFavourite.setImageDrawable(favouriteUnchecked)
                    btnAddToFavourite.setOnClickListener{
                        detailViewModel.insertMovie(movie)
                    }
                } else {
                    btnAddToFavourite.setImageDrawable(favouriteChecked)
                    btnAddToFavourite.setOnClickListener{
                        detailViewModel.removeMovie(movie.id)
                    }
                }
            }
        )

        detailViewModel.loadTrailers(movie.id)

        detailViewModel.trailer.observe(
            viewLifecycleOwner,
            Observer {
                val trailer = it
                btnTrailer.setOnClickListener{
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setData(Uri.parse(trailer.url))
                    startActivity(intent)
                }
            }
        )

        val reviewsAdapter = ReviewsAdapter()
        recyclerViewReviews.layoutManager = reviewsLayoutManager
        recyclerViewReviews.adapter = reviewsAdapter

        detailViewModel.reviews.observe(
            viewLifecycleOwner,
            Observer {
                reviewsAdapter.setReviews(it)
            }
        )

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        detailViewModel.loadReviews(movie.id)
        reviewsAdapter.setOnItemClickListener(
            object : ReviewsAdapter.OnItemClickListener{
                override fun onItemClick(review: Review) {
                    val bundle = Bundle()
                    bundle.putSerializable("review", review)
                    navController.navigate(R.id.modalBottomSheet, bundle)
                }
            }
        )


        return view
    }

    fun initViews(view : View){
        imageViewDetail = view.findViewById(R.id.imageViewDetail)
        tvName = view.findViewById(R.id.tvNameMore)
        tvYear = view.findViewById(R.id.tvYearMore)
        tvDescription = view.findViewById(R.id.tvDescriptionMore)
        btnAddToFavourite = view.findViewById(R.id.btnAddToFavourite)
        btnTrailer = view.findViewById(R.id.btnTrailer)
        favouriteUnchecked = ContextCompat.getDrawable(view.context, R.drawable.add_to_favourite)!!
        favouriteChecked = ContextCompat.getDrawable(view.context,
            R.drawable.add_to_favourite_checked
        )!!
        recyclerViewReviews = view.findViewById(R.id.recyclerViewReviews)
        reviewsLayoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
    }
}