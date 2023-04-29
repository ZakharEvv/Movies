package com.example.moviescourse.Model.Review

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("title")
    val title: String,
    @SerializedName("review")
    val review : String,
    @SerializedName("author")
    val author : String,
) : java.io.Serializable
