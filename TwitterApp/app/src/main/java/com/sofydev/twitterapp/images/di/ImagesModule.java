package com.sofydev.twitterapp.images.di;

import com.sofydev.twitterapp.api.CustomTwitterApiClient;
import com.sofydev.twitterapp.entities.Image;
import com.sofydev.twitterapp.images.ImagesInteractor;
import com.sofydev.twitterapp.images.ImagesInteractorImpl;
import com.sofydev.twitterapp.images.ImagesPresenter;
import com.sofydev.twitterapp.images.ImagesPresenterImpl;
import com.sofydev.twitterapp.images.ImagesRepository;
import com.sofydev.twitterapp.images.ImagesRepositoryImpl;
import com.sofydev.twitterapp.images.adapters.ImagesAdapter;
import com.sofydev.twitterapp.images.adapters.OnItemClickListener;
import com.sofydev.twitterapp.images.ui.ImagesView;
import com.sofydev.twitterapp.lib.base.EventBus;
import com.sofydev.twitterapp.lib.base.ImageLoader;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ImagesModule {

    private ImagesView view;
    private OnItemClickListener clickListener;

    public ImagesModule(ImagesView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }


    //We need the adapter
    @Provides
    @Singleton
        //ImageLoader comes from the LibsModule
    ImagesAdapter providesAdapter(List<Image> dataset, ImageLoader imageLoader, OnItemClickListener clickListener) {
        return new ImagesAdapter(dataset, imageLoader, clickListener);
    }


    @Provides
    @Singleton
        //In case i want to use another click listener?
    OnItemClickListener providesOnItemClickListener() {
        return this.clickListener;
    }


    @Provides
    @Singleton
    List<Image> providesItemsList() {
        return new ArrayList<>();
    }

    @Provides
    @Singleton
    ImagesView providesImagesView() {
        return this.view;
    }


    //And we need the presenter
    @Provides
    @Singleton
        //EventBus comes from the LibsModule and the view comes from the constructor
    ImagesPresenter providesPresenter(EventBus eventBus, ImagesView view, ImagesInteractor interactor) {
        return new ImagesPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    ImagesInteractor providesImagesInteractor(ImagesRepository repository) {
        return new ImagesInteractorImpl(repository);
    }

    @Provides
    @Singleton
        //EventBus comes from the LibsModule
    ImagesRepository providesImagesRepository(EventBus eventBus, CustomTwitterApiClient client) {
        return new ImagesRepositoryImpl(eventBus, client);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(Session session) {
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    Session providesTwitterSession() {
        return Twitter.getSessionManager().getActiveSession();
    }
}
