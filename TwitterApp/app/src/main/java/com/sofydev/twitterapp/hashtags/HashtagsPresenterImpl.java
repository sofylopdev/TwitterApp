package com.sofydev.twitterapp.hashtags;

import com.sofydev.twitterapp.hashtags.events.HashtagsEvent;
import com.sofydev.twitterapp.hashtags.ui.HashtagsView;
import com.sofydev.twitterapp.lib.base.EventBus;

public class HashtagsPresenterImpl implements HashtagsPresenter {
    private HashtagsView view;
    private EventBus eventBus;
    private HashtagsInteractor interactor;

    public HashtagsPresenterImpl(HashtagsView view, EventBus eventBus, HashtagsInteractor interactor) {
        this.view = view;
        this.eventBus = eventBus;
        this.interactor = interactor;
    }

    @Override
    public void onResume() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getHashtagsTweets() {
        if (view != null) {
            view.hideHashtags();
            view.showProgress();
        }
        interactor.execute();
    }

    @Override
    public void onEventMainThread(HashtagsEvent event) {
        String errorMsg = event.getError();
        if (view != null) {
            view.showHashtags();
            view.hideProgress();

            if(errorMsg != null){
                view.onError(errorMsg);
            }else{
                view.setContent(event.getHashtags());
            }
        }
    }
}
