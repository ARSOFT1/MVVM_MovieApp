package com.gaffyhoares.data.repository

import androidx.lifecycle.MutableLiveData
import com.gaffyhoares.data.network.MyApi
import com.gaffyhoares.data.network.response.MoviesResult
import com.gaffyhoares.utils.safeApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class MovieRepository @Inject constructor(private val myApi: MyApi) {

    private val mutableLiveData = MutableLiveData<List<MoviesResult>>()

    fun getMutableLiveData(): MutableLiveData<List<MoviesResult>> {
        GlobalScope.launch {
            val api = safeApi { myApi.getTrendingMovies() }
            if (api.results != null) {
                mutableLiveData.postValue(api.results)
            }
        }

        return mutableLiveData
    }
}