package com.shubham.mahalkar.movieappmvvm.data.api

import com.shubham.mahalkar.movieappmvvm.data.vo.MovieDetails
import com.shubham.mahalkar.movieappmvvm.data.vo.MovieResponse
import io.reactivex.Single
import io.reactivex.SingleObserver
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBInterface {

    @GET("movie/popular")
    fun getPopularMovie(@Query("page")page: Int): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>
}