package com.gaffyhoares.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gaffyhoares.data.network.response.MoviesResult
import com.gaffyhoares.data.repository.MovieRepository

class MainViewModel : ViewModel() {

    private var movieRepository: MovieRepository? = null

    init {
        movieRepository = MovieRepository()
    }

    fun getMoviesList():LiveData<List<MoviesResult>>{
        return movieRepository?.getMutableLiveData()!!
    }
}