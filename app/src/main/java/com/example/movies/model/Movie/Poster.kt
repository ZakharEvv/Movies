package com.example.moviescourse.Model.Movie

import com.google.gson.annotations.SerializedName

data class Poster(
    @SerializedName("url")
    val url: String
    ) : java.io.Serializable