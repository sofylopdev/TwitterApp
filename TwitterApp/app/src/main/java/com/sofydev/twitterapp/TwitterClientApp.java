package com.sofydev.twitterapp;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.sofydev.twitterapp.hashtags.di.DaggerHashtagsComponent;
import com.sofydev.twitterapp.hashtags.di.HashtagsComponent;
import com.sofydev.twitterapp.hashtags.di.HashtagsModule;
import com.sofydev.twitterapp.hashtags.ui.HashtagsView;
import com.sofydev.twitterapp.images.adapters.OnItemClickListener;
import com.sofydev.twitterapp.images.di.DaggerImagesComponent;
import com.sofydev.twitterapp.images.di.ImagesComponent;
import com.sofydev.twitterapp.images.di.ImagesModule;
import com.sofydev.twitterapp.images.ui.ImagesView;
import com.sofydev.twitterapp.lib.di.LibsModule;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

public class TwitterClientApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initFabric();
    }

    private void initFabric() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }

    public ImagesComponent getImagesComponent(Fragment fragment, ImagesView view, OnItemClickListener clickListener) {
        return DaggerImagesComponent
                .builder()
                .libsModule(new LibsModule(fragment))
                .imagesModule(new ImagesModule(view, clickListener))
                .build();
    }

    public HashtagsComponent getHashtagsComponent(HashtagsView view, com.sofydev.twitterapp.hashtags.ui.adapters.OnItemClickListener listener) {
        return DaggerHashtagsComponent
                .builder()
                .libsModule(new LibsModule(null))//this was only needed for the imagesLoader
                .hashtagsModule(new HashtagsModule(view, listener))
                .build();
    }
}
