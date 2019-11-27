package com.gaffyhoares.data.repository

import androidx.lifecycle.MutableLiveData
import com.gaffyhoares.data.network.MyApi
import com.gaffyhoares.data.network.response.MoviesResult
import com.gaffyhoares.utils.safeApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.gahfy.feedme.utils.API_KEY
import javax.inject.Inject


class MovieRepository @Inject constructor(private val myApi: MyApi) {

    private val mutableLiveData = MutableLiveData<List<MoviesResult>>()

    fun getMutableLiveData(year: String): MutableLiveData<List<MoviesResult>> {
        GlobalScope.launch {
            val api = safeApi { myApi.getTrendingMovies(year, API_KEY) }
            if (api.results != null) {
                mutableLiveData.postValue(api.results)
            }
        }
        return mutableLiveData
    }

    fun getTopPopularMovies(): MutableLiveData<List<MoviesResult>> {

        GlobalScope.launch {
            val api = safeApi { myApi.getPopularMovies() }
            if (api.results != null) {
                mutableLiveData.postValue(api.results)
            }
        }
        return mutableLiveData
    }

    fun getTopUpComing(): MutableLiveData<List<MoviesResult>> {

        GlobalScope.launch {
            val api = safeApi { myApi.getUpComingMovies() }
            if (api.results != null) {
                mutableLiveData.postValue(api.results)
            }
        }
        return mutableLiveData
    }

    fun getTopRatedMovies(): MutableLiveData<List<MoviesResult>> {

        GlobalScope.launch {
            val api = safeApi { myApi.getTopRatedMovies() }
            if (api.results != null) {
                mutableLiveData.postValue(api.results)
            }
        }
        return mutableLiveData
    }
}