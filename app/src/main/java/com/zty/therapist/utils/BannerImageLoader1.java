package com.zty.therapist.utils;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * Created by zty on 2017/1/5.
 */

public class BannerImageLoader1 extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        MyImageLoader.load(context, (int) path, imageView);
    }
}
