package com.shubham.mahalkar.movieappmvvm.movie_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.shubham.mahalkar.movieappmvvm.R
import com.shubham.mahalkar.movieappmvvm.data.api.MovieDBClient
import com.shubham.mahalkar.movieappmvvm.data.api.MovieDBInterface
import com.shubham.mahalkar.movieappmvvm.data.api.POSTER_BASE_URL
import com.shubham.mahalkar.movieappmvvm.data.repository.NetworkState
import com.shubham.mahalkar.movieappmvvm.data.vo.MovieDetails
import kotlinx.android.synthetic.main.activity_single_movie.*
import kotlinx.android.synthetic.main.content_single_movie.*
import java.text.NumberFormat
import java.util.*

class ActivitySingleMovie : AppCompatActivity() {

    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieDetailRepository: MovieDetailRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)

        val movieId: Int = intent.getIntExtra("id", 1)
        val apiService: MovieDBInterface = MovieDBClient.getClient()
        movieDetailRepository = MovieDetailRepository(apiService)

        viewModel = getViewModel(movieId)
        viewModel.movieDetails.observe(this, Observer {
            bindUI(it)
        })

        viewModel.networkState.observe(this, Observer {
            progress_circular.visibility =
                if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            tvErrorMessage.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE
        })
    }

    private fun bindUI(it: MovieDetails) {
        tvMovieTitle.text = it.title
        tvMovieSubTitle.text = it.tagline
        tvReleaseDate.text = it.releaseDate
        tvRating.text = it.rating.toString()
        tvRuntime.text = it.runtime.toString() + " minutes"
        tvOverview.text = it.overview

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        tvBudget.text = formatCurrency.format(it.budget)
        tvRevenue.text = formatCurrency.format(it.revenue)

        val moviePosterUrl = POSTER_BASE_URL + it.posterPath
        Glide.with(this).load(moviePosterUrl).into(ivMovieThumbnail)
    }

    private fun getViewModel(movieId: Int): SingleMovieViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SingleMovieViewModel(movieDetailRepository, movieId) as T
            }
        })[SingleMovieViewModel::class.java]
    }
}