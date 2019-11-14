package com.gaffyhoares.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtils {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMG_BASE_URL = "http://image.tmdb.org/t/p/w185/"
    private lateinit var retrofit: Retrofit

    fun getMyApi(): MyApi {
        return Retrofit.Builder()
            .client(OkHttpClient())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MyApi::class.java)
    }
}