package com.example.moviescourse.Model.Movie


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "favourite_movies")
data class Movie (

    @PrimaryKey
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("year")
    val year : Int,
    @Embedded
    @SerializedName("poster")
    val poster: Poster,
    @Embedded
    @SerializedName("rating")
    val rating: Rating
) : java.io.Serializable