package com.gaffyhoares.data.di;

import androidx.lifecycle.ViewModel;

import com.gaffyhoares.ui.movies.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindMainViewModelModule(MainViewModel model);
}
