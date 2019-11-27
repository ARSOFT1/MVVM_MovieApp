package com.gaffyhoares.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gaffyhoares.data.network.response.MoviesResult
import com.gaffyhoares.data.repository.MovieRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private lateinit var result: MutableLiveData<List<MoviesResult>>

    fun getMoviesList(year: String): LiveData<List<MoviesResult>> {
        return movieRepository.getMutableLiveData(year)
    }

    fun getTopRatedMovieList(move_category: String): LiveData<List<MoviesResult>> {
        if (move_category.equals("popular")) {
            return movieRepository.getTopPopularMovies()
        }else if (move_category.equals("upcoming")){
            return movieRepository.getTopUpComing()
        }else{
            return movieRepository.getTopRatedMovies()
        }
    }
}