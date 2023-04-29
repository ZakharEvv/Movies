package com.example.movies.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviescourse.Database.MovieDatabase
import com.example.moviescourse.Model.Movie.Movie
import com.example.moviescourse.Model.Review.Review
import com.example.moviescourse.Network.ApiFactory
import com.example.moviescourse.Model.Trailer.Trailer
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

class  DetailViewModel(application: Application) : AndroidViewModel(application) {

    var trailer = MutableLiveData<Trailer>()
    val compositeDisposable = CompositeDisposable()
    var reviews = MutableLiveData<List<Review>>()
    val movieDatabase = MovieDatabase.getInstance(application)

    fun getFavouriteMovie(movieId : Int) : LiveData<Movie>{
        return movieDatabase.movieDao().getFavouriteMovie(movieId)
    }

    fun insertMovie(movie: Movie){
        val disposable = movieDatabase.movieDao().insertMovie(movie)
            .subscribeOn(Schedulers.io())
            .subscribe()
        compositeDisposable.add(disposable)
    }

    fun removeMovie(movieId: Int){
        val disposable = movieDatabase.movieDao().removeMovie(movieId)
            .subscribeOn(Schedulers.io())
            .subscribe()
        compositeDisposable.add(disposable)
    }

    fun loadTrailers(id : Int){
        val disposable = ApiFactory.apiService.loadTrailers(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    trailer.value = it.trailersList.trailers.first()
                },
                { }
            )
        compositeDisposable.add(disposable)
    }

    fun loadReviews(id : Int){
        val disposable = ApiFactory.apiService.loadReviews(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                Consumer {
                    reviews.value = it.reviews
                },
                {}
            )
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}