package com.example.moviescourse.Model.Movie

import com.google.gson.annotations.SerializedName

class Poster(
    @SerializedName("url")
    val url: String
    ) : java.io.Serializable{}