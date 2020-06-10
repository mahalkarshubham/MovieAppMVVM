package com.shubham.mahalkar.movieappmvvm.data.api

import com.shubham.mahalkar.movieappmvvm.data.vo.MovieDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDBInterface {

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int):Single<MovieDetails>
}