package com.gaffyhoares.data.dependency

import android.app.Application
import com.gaffyhoares.AppController
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ApiModule::class])
interface AppComponent {

   /* @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(appController: AppController)*/
}