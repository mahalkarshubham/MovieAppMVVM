package com.shubham.mahalkar.movieappmvvm.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shubham.mahalkar.movieappmvvm.data.api.MovieDBInterface
import com.shubham.mahalkar.movieappmvvm.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class MovieDetailsData(
    private val apiService: MovieDBInterface,
    private val compositeDisposable: CompositeDisposable
) {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _getMovieDetail = MutableLiveData<MovieDetails>()
    val getMovieDetail: LiveData<MovieDetails>
        get() = _getMovieDetail

    fun fetchMovieDetails(movieId: Int) {
        _networkState.postValue(NetworkState.LOADING)

        try {
            compositeDisposable.add(
                apiService.getMovieDetails(movieId).subscribeOn(Schedulers.io()).subscribe(
                    {
                        _getMovieDetail.postValue(it)
                        _networkState.postValue(NetworkState.LOADED)
                    }, {
                        _networkState.postValue(NetworkState.ERROR)
                        Log.e("MovieDetails", it.message)
                    }
                )
            )
        } catch (e: Exception) {
            Log.e("MovieDetails", e.message)
        }
    }
}