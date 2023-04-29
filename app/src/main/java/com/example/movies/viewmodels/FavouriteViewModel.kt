package com.example.movies.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.moviescourse.Database.MovieDatabase
import com.example.moviescourse.Model.Movie.Movie
import io.reactivex.rxjava3.disposables.CompositeDisposable

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {

    val movieDatabase = MovieDatabase.getInstance(application)
    val compositeDisposable = CompositeDisposable()

    fun getMovies() : LiveData<List<Movie>> {
        return movieDatabase.movieDao().getAllFavouriteMovies()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}