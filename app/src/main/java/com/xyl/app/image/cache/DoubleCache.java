package com.xyl.app.image.cache;

import android.graphics.Bitmap;

import com.xyl.app.image.request.BitmapRequest;

/**
 * @author xyl on 2019/4/4.
 */
public class DoubleCache implements BitmapCache {
    private MemoryCache memoryCache = new MemoryCache();
    private DiskCache diskCache = DiskCache.getInstance();

    public DoubleCache() {
    }

    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        memoryCache.put(request, bitmap);
        diskCache.put(request, bitmap);
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        Bitmap bitmap = memoryCache.get(request);
        if (bitmap == null) {
            bitmap = diskCache.get(request);
            if (bitmap != null) {
                //放入内存
                memoryCache.put(request, bitmap);
            }
        }
        return bitmap;
    }

    @Override
    public void remove(BitmapRequest request) {
        memoryCache.remove(request);
        diskCache.remove(request);
    }
}
