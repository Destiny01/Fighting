package com.xyl.app.image.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.xyl.app.image.request.BitmapRequest;

/**
 * 内存缓存
 * 内存缓存，这里面主要使用的是LruCache.
 * 一般情况下设置的LruCache的大小为系统内存的1/8，
 * 只需重写sizeOf方法测量每个图片所占用的内存，图片占用的内存为一行的字节数*高即为每张图片的大小
 *
 * @author xyl on 2019/4/4.
 */
public class MemoryCache implements BitmapCache {
    private LruCache<String, Bitmap> lruCache;

    public MemoryCache() {

        //一般情况下设置的LruCache的大小为系统内存的1/8，
        int maxSize = (int) (Runtime.getRuntime().freeMemory() / 1024 / 8);

        lruCache = new LruCache<String, Bitmap>(maxSize) {

            @Override
            protected int sizeOf(String key, Bitmap value) {
                //计算图片内存大小：图片占用的内存=一行的字节数*高=即为每张图片的大小
                return value.getRowBytes() * value.getHeight();
            }

        };

    }

    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        lruCache.put(request.getImageUriMD5(), bitmap);
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        return lruCache.get(request.getImageUriMD5());
    }

    @Override
    public void remove(BitmapRequest request) {
        lruCache.remove(request.getImageUriMD5());
    }
}
