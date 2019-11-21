package com.gaffyhoares.data.network


import com.gaffyhoares.data.network.response.DataResponse
import com.gaffyhoares.data.network.response.MoviesResult
import retrofit2.Call
import retrofit2.http.GET

interface MyApi {

    @GET("trending/movie/day?api_key=d85176d3c754f545365caa4a5a9d84f5")
    suspend fun getTrendingMovies(): DataResponse<List<MoviesResult>>

}