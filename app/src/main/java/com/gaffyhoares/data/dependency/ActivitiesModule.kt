package com.gaffyhoares.data.dependency

import com.gaffyhoares.ui.movies.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity
}