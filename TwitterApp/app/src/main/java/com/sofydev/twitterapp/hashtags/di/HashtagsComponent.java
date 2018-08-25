package com.sofydev.twitterapp.hashtags.di;

import com.sofydev.twitterapp.hashtags.ui.HashtagsFragment;
import com.sofydev.twitterapp.lib.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton @Component(modules = {LibsModule.class, HashtagsModule.class})
public interface HashtagsComponent {

    void inject(HashtagsFragment fragment);
}
