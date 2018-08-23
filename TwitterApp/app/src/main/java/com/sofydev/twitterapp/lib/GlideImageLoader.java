package com.sofydev.twitterapp.lib;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sofydev.twitterapp.lib.base.ImageLoader;

public class GlideImageLoader implements ImageLoader {

    private RequestManager glideRequestManager;

    public GlideImageLoader(RequestManager requestManager) {//Fragment fragment
        this.glideRequestManager = requestManager;//Glide.with(fragment)
    }

    @Override
    public void load(ImageView imageView, String URL) {
glideRequestManager
        .load(URL)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()
        .override(600, 400)
        .into(imageView);
    }
}
