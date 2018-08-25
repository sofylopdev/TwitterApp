package com.sofydev.twitterapp.images.ui;

import com.sofydev.twitterapp.entities.Image;

import java.util.List;

public interface ImagesView {
    void showImages();
    void hideImages();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Image> items);
}
