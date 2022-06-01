package com.example.nanodg.tambalban.ir.apend.slider.ui.customUI;

import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.Resource;

public interface RoundedCornersTransformations {
    Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight);

    String getId();
}
