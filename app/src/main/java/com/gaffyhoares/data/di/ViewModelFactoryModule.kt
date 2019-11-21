package com.gaffyhoares.data.di

import androidx.lifecycle.ViewModelProvider
import com.gaffyhoares.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProviderFactory?): ViewModelProvider.Factory?
}