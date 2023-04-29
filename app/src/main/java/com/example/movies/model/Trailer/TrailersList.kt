package com.example.moviescourse.Model.Trailer

import com.example.moviescourse.Model.Trailer.Trailer
import com.google.gson.annotations.SerializedName

data class TrailersList(
    @SerializedName("trailers")
    val trailers : List<Trailer>
)
