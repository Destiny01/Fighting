package com.xyl.app.image;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * @author xyl on 2019/4/4.
 */
public interface ImageListener {
    void onComplete(ImageView imageView, Bitmap bitmap, String imageUrl);
}
