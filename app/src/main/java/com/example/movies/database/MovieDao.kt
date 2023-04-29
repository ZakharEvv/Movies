package com.example.moviescourse.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.moviescourse.Model.Movie.Movie
import io.reactivex.rxjava3.core.Completable

@Dao
interface MovieDao {

    @Query("SELECT * FROM favourite_movies")
    fun getAllFavouriteMovies() : LiveData<List<Movie>>

    @Query("SELECT * FROM favourite_movies WHERE id = :movieId")
    fun getFavouriteMovie(movieId : Int) : LiveData<Movie>

    @Insert
    fun insertMovie(movie : Movie) : Completable

    @Query("DELETE FROM favourite_movies WHERE id = :movieId")
    fun removeMovie(movieId : Int) : Completable
}