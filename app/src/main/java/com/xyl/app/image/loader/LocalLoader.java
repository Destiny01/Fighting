package com.xyl.app.image.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.xyl.app.image.util.ImageViewHelper;
import com.xyl.app.image.request.BitmapRequest;

import java.io.File;

/**
 * @author xyl on 2019/4/4.
 */
public class LocalLoader extends AbstarctLoader {
    @Override
    protected Bitmap onLoad(BitmapRequest request) {
        //得到本地图片的路径
        final String path = Uri.parse(request.getImageUrl()).getPath();
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        BitmapDecoder decoder = new BitmapDecoder() {
            @Override
            public Bitmap decodeBitmapWithOption(BitmapFactory.Options options) {
                return BitmapFactory.decodeFile(path, options);
            }
        };

        return decoder.decodeBitmap(ImageViewHelper.getImageViewWidth(request.getImageView())
                , ImageViewHelper.getImageViewHeight(request.getImageView()));
    }
}
