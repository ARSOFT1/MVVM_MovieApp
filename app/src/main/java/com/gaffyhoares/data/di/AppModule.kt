/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gaffyhoares.data.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.gaffyhoares.data.network.MyApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule(urlPath: String) {

    private val urlPath: String

    init {
        this.urlPath = urlPath
    }


    @Singleton
    @Provides
    fun provideGson(): Gson {
        val builder = GsonBuilder()
        return builder.create()
    }
//    httpClient.addNetworkInterceptor(StethoInterceptor())
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        return httpClient.build()
    }

    @Singleton
    @Provides
    fun provideMyApiService(gson: Gson, okHttpClient: OkHttpClient): MyApi {
        return Retrofit.Builder()
            .baseUrl(urlPath)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(MyApi::class.java)
    }

    /* @Singleton
     @Provides
     fun provideDb(app: Application): GithubDb {
         return Room
             .databaseBuilder(app, GithubDb::class.java, "github.db")
             .fallbackToDestructiveMigration()
             .build()
     }

     @Singleton
     @Provides
     fun provideUserDao(db: GithubDb): UserDao {
         return db.userDao()
     }

     @Singleton
     @Provides
     fun provideRepoDao(db: GithubDb): RepoDao {
         return db.repoDao()
     }*/
}
