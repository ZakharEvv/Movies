package com.example.moviescourse.Network

import com.example.moviescourse.Model.Movie.MovieResponse
import com.example.moviescourse.Model.Review.ReviewResponse
import com.example.moviescourse.Model.Trailer.TrailerResponse
import com.example.moviescourse.Model.Trailer.TrailersList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/movie?token=H1ATZN3-GZG4VPP-Q22S613-P7V686P&field=rating.kp&search=7-10&sortField=votes.kp&sortType=-1&limit=20")
    fun loadMovies(@Query("page") page: Int) : Single<MovieResponse>

    @GET("movie?token=H1ATZN3-GZG4VPP-Q22S613-P7V686P&field=id")
    fun loadTrailers(@Query("search") id: Int) : Single<TrailerResponse>

    @GET("review?token=H1ATZN3-GZG4VPP-Q22S613-P7V686P&field=movieId")
    fun loadReviews(@Query("search") id: Int) : Single<ReviewResponse>
}