package com.example.moviescourse.Model.Review

import com.example.moviescourse.Model.Movie.Movie
import com.example.moviescourse.Model.Trailer.TrailersList
import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @SerializedName("docs")
    val reviews : List<Review>
)
