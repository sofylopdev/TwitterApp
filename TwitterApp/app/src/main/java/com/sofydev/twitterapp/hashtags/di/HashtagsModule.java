package com.sofydev.twitterapp.hashtags.di;

import com.sofydev.twitterapp.api.CustomTwitterApiClient;
import com.sofydev.twitterapp.entities.Hashtag;
import com.sofydev.twitterapp.hashtags.HashtagsInteractor;
import com.sofydev.twitterapp.hashtags.HashtagsInteractorImpl;
import com.sofydev.twitterapp.hashtags.HashtagsPresenter;
import com.sofydev.twitterapp.hashtags.HashtagsPresenterImpl;
import com.sofydev.twitterapp.hashtags.HashtagsRepository;
import com.sofydev.twitterapp.hashtags.HashtagsRepositoryImpl;
import com.sofydev.twitterapp.hashtags.ui.HashtagsView;
import com.sofydev.twitterapp.hashtags.ui.adapters.HashtagsAdapter;
import com.sofydev.twitterapp.hashtags.ui.adapters.OnItemClickListener;
import com.sofydev.twitterapp.lib.base.EventBus;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class HashtagsModule {
    private HashtagsView view;
    private OnItemClickListener clickListener;

    public HashtagsModule(HashtagsView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    HashtagsView getHashtagsView() {
        return this.view;
    }

    @Provides
    @Singleton
    OnItemClickListener getOnItemClickListener() {
        return this.clickListener;
    }

    @Provides
    @Singleton
    HashtagsAdapter providesAdapter(List<Hashtag> items, OnItemClickListener listener) {
        return new HashtagsAdapter(items, listener);
    }

    @Provides
    @Singleton
    List<Hashtag> providesItemsList() {
        return new ArrayList<Hashtag>();
    }


    @Provides
    @Singleton
    HashtagsPresenter providesHashtagsPresenter(HashtagsView view, EventBus eventBus, HashtagsInteractor interactor) {
        return new HashtagsPresenterImpl(view, eventBus, interactor);
    }

    @Provides
    @Singleton
    HashtagsInteractor providesInteractor(HashtagsRepository repository) {
        return new HashtagsInteractorImpl(repository);
    }

    @Provides
    @Singleton
    HashtagsRepository providesRepository(EventBus eventBus, CustomTwitterApiClient client) {
        return new HashtagsRepositoryImpl(eventBus, client);
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
