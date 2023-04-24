package com.example.moviescourse.Model.Movie

import com.example.moviescourse.Model.Movie.Movie
import com.google.gson.annotations.SerializedName

class MovieResponse(
    @SerializedName("docs")
    val movies : List<Movie>
) {}