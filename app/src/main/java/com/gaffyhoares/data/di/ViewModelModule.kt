package com.gaffyhoares.data.di

import androidx.lifecycle.ViewModel
import com.gaffyhoares.ui.movies.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModelModule(model: MainViewModel?): ViewModel?
}