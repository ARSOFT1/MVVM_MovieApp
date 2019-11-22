package com.gaffyhoares.data.di

import com.gaffyhoares.MyApplication
import com.gaffyhoares.ui.movies.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelFactoryModule::class])
interface AppComponent {
    fun inject(myApplication: MyApplication)
    fun inject(activity: HomeActivity)
}