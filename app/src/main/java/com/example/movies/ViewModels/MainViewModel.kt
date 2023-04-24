package com.example.movies.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviescourse.Model.Movie.Movie
import com.example.moviescourse.Model.Movie.MovieResponse
import com.example.moviescourse.Network.ApiFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var movies = MutableLiveData<List<Movie>>()
    var isLoading = MutableLiveData<Boolean>(false)
    var compositeDisposable = CompositeDisposable()
    var page = 1

    init {
        loadMovies()
    }

    fun getMovies() : LiveData<List<Movie>> {
        return movies
    }

    fun getIsLoading() : LiveData<Boolean> {
        return isLoading
    }

    fun loadMovies(){
        val loading = isLoading.value
        if (loading!=null && loading==true)
            return

        val disposable = ApiFactory.apiService.loadMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(
                object : Consumer<Disposable> {
                    override fun accept(t: Disposable) {
                        isLoading.value = true
                    }
                }
            )
            .doAfterTerminate(
                object : Action {
                    override fun run() {
                        isLoading.value = false
                    }
                }
            )
            .subscribe(
                object : Consumer<MovieResponse> {
                    override fun accept(movieResponse: MovieResponse) {
                        val loadedMovies = ArrayList<Movie>(1)
                        movies.value?.let { loadedMovies.addAll(it) }

                        if(loadedMovies.isNotEmpty()) {
                            loadedMovies.addAll(movieResponse.movies)
                            movies.value = loadedMovies
                        } else
                            movies.value = movieResponse.movies
                        page++
                    }
                },
                object : Consumer<Throwable> {
                    override fun accept(t: Throwable) {

                    }
                }
            )
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}