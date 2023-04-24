package com.example.moviescourse.Model.Trailer

import com.google.gson.annotations.SerializedName

data class TrailerResponse(
    @SerializedName("videos")
    val trailersList : TrailersList
)
