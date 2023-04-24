package com.example.moviescourse.Model.Movie

import com.google.gson.annotations.SerializedName

class Rating(
    @SerializedName("kp")
    val kp : Double
    ) : java.io.Serializable{}