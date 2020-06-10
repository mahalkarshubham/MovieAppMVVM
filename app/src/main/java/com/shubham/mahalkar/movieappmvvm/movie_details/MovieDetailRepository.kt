package com.shubham.mahalkar.movieappmvvm.movie_details

import android.view.autofill.AutofillId
import androidx.lifecycle.LiveData
import com.shubham.mahalkar.movieappmvvm.data.api.MovieDBInterface
import com.shubham.mahalkar.movieappmvvm.data.repository.MovieDetailsData
import com.shubham.mahalkar.movieappmvvm.data.repository.NetworkState
import com.shubham.mahalkar.movieappmvvm.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailRepository(private val apiService: MovieDBInterface) {

    lateinit var movieDetailsData: MovieDetailsData

    fun fetchSingleMovieDetails(
        compositeDisposable: CompositeDisposable,
        movieId: Int
    ): LiveData<MovieDetails> {
        movieDetailsData = MovieDetailsData(apiService, compositeDisposable)
        movieDetailsData.fetchMovieDetails(movieId)

        return movieDetailsData.getMovieDetail
    }

    fun getMovieDetailsNetworkState():LiveData<NetworkState>{
        return movieDetailsData.networkState
    }
}