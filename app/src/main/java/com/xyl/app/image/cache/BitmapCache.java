package com.xyl.app.image.cache;

import android.graphics.Bitmap;

import com.xyl.app.image.request.BitmapRequest;

/**
 * 缓存策略接口
 *
 * @author xyl on 2019/4/4.
 */
public interface BitmapCache {
    /**
     * 缓存bitmap
     *
     * @param request
     * @param bitmap
     */
    void put(BitmapRequest request, Bitmap bitmap);

    /**
     * 通过请求获取Bitmap
     *
     * @param request
     * @return
     */
    Bitmap get(BitmapRequest request);

    /**
     * 移除缓存
     *
     * @param request
     */
    void remove(BitmapRequest request);
}
