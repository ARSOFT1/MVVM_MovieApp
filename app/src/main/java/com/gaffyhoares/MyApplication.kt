package com.gaffyhoares

import android.app.Application
import com.facebook.stetho.Stetho
import com.gaffyhoares.data.di.AppComponent
import com.gaffyhoares.data.di.AppModule
import com.gaffyhoares.data.di.DaggerAppComponent
import net.gahfy.feedme.utils.BASE_URL

class MyApplication : Application() {
    var appComponent: AppComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        appComponent = DaggerAppComponent.builder().appModule(AppModule(BASE_URL)).build()
    }

}