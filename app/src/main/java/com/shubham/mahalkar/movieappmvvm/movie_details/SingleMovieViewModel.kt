package com.shubham.mahalkar.movieappmvvm.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shubham.mahalkar.movieappmvvm.data.repository.NetworkState
import com.shubham.mahalkar.movieappmvvm.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class SingleMovieViewModel(private val movieRepository: MovieDetailRepository, movieId: Int) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val movieDetails : LiveData<MovieDetails> by lazy {
        movieRepository.fetchSingleMovieDetails(compositeDisposable, movieId)
    }
    val networkState : LiveData<NetworkState> by lazy {
        movieRepository.getMovieDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

