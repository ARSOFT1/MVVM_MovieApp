package com.gaffyhoares.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gaffyhoares.data.network.response.MoviesResult
import com.gaffyhoares.data.repository.MovieRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {


    fun getMoviesList(): LiveData<List<MoviesResult>> {
        return movieRepository.getMutableLiveData()
    }

}