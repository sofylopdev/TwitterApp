package com.sofydev.twitterapp.images;

import com.sofydev.twitterapp.images.events.ImagesEvent;

public interface ImagesPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getImageTweets();
    void onEventMainThread(ImagesEvent event);
}
