package com.sofydev.twitterapp.hashtags;

import com.sofydev.twitterapp.hashtags.events.HashtagsEvent;

public interface HashtagsPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getHashtagsTweets();
    void onEventMainThread(HashtagsEvent event);
}
