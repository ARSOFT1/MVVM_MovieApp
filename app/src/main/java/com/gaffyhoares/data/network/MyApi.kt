package com.gaffyhoares.data.network


import com.gaffyhoares.data.network.response.DataResponse
import com.gaffyhoares.data.network.response.MoviesResult
import retrofit2.http.GET
import retrofit2.http.Query


interface MyApi {
    //    ?primary_release_year=2018&api_key=d85176d3c754f545365caa4a5a9d84f5
    @GET("discover/movie")
    suspend fun getTrendingMovies(@Query("primary_release_year") year: String, @Query("api_key") apiKey: String): DataResponse<List<MoviesResult>>

}