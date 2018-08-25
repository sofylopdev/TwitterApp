package com.sofydev.twitterapp.images.di;


import com.sofydev.twitterapp.images.ImagesPresenter;
import com.sofydev.twitterapp.images.ui.ImagesFragment;
import com.sofydev.twitterapp.lib.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton @Component(modules = {LibsModule.class, ImagesModule.class})
public interface ImagesComponent {

    void inject(ImagesFragment fragment);
    //or
    ImagesPresenter getPresenter();

}
