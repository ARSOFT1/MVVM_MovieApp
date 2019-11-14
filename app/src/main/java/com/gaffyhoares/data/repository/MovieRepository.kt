package com.gaffyhoares.data.repository

import android.app.Application
import android.content.Context
import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import com.gaffyhoares.Constant
import com.gaffyhoares.data.network.ApiUtils
import com.gaffyhoares.data.network.response.MoviesResult
import com.gaffyhoares.safeApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MovieRepository {

    private val mutableLiveData = MutableLiveData<List<MoviesResult>>()

    fun getMutableLiveData():MutableLiveData<List<MoviesResult>>{
        GlobalScope.launch {
            val api = safeApi {ApiUtils.getMyApi().getTrendingMovies()}
            if (api.results != null) {
//                mutableLiveData.value = api.results
                mutableLiveData.postValue(api.results)
            }
        }
        return mutableLiveData
    }
}