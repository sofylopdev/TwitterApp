package com.sofydev.twitterapp.hashtags.ui;

import com.sofydev.twitterapp.entities.Hashtag;

import java.util.List;

public interface HashtagsView {
    void showHashtags();
    void hideHashtags();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Hashtag> items);
}
