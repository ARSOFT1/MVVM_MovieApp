package com.gaffyhoares.data.di;

import com.gaffyhoares.MyApplication;
import com.gaffyhoares.ui.movies.HomeActivity;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class,ViewModelFactoryModule.class,})
public interface AppComponent {

    void inject(MyApplication myApplication);

    void inject(HomeActivity activity);
}
