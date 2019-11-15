package com.gaffyhoares.data.repository

import androidx.lifecycle.MutableLiveData
import com.gaffyhoares.data.network.ApiUtils
import com.gaffyhoares.data.network.response.MoviesResult
import com.gaffyhoares.utils.safeApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MovieRepository {

/*    @Inject
    lateinit var myApi: MyApi*/

    private val mutableLiveData = MutableLiveData<List<MoviesResult>>()

    fun getMutableLiveData(): MutableLiveData<List<MoviesResult>> {
        GlobalScope.launch {
            val api = safeApi { ApiUtils.getMyApi().getTrendingMovies() }
            if (api.results != null) {
                mutableLiveData.postValue(api.results)
            }
        }
        return mutableLiveData
    }
}