package com.gaffyhoares.data.dependency

import com.gaffyhoares.MyApplication
import dagger.Component

@Component(modules = [ActivitiesModule::class])
interface AppComponent {
    fun inject(myApplication: MyApplication)
}