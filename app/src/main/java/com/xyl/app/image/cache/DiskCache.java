package com.xyl.app.image.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.xyl.app.image.request.BitmapRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 硬盘缓存
 *
 * @author xyl on 2019/4/4.
 */
public class DiskCache implements BitmapCache {
    private static DiskCache mDiskCache;
    //缓存路径
    private String mCacheDir = "Image";
    //MB
    private static final int MB = 1024 * 1024;
    //jackwharton的杰作
    private DiskLruCache mDiskLruCache;

    private DiskCache() {
        iniDiskCache();
    }

    public static DiskCache getInstance() {
        if (mDiskCache == null) {
            synchronized (DiskCache.class) {
                if (mDiskCache == null) {
                    mDiskCache = new DiskCache();
                }
            }
        }
        return mDiskCache;
    }

    private void iniDiskCache() {
        //得到缓存的目录  android/data/data/包名/cache/Image
        File directory = getDiskCache(mCacheDir);
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            //最后一个参数 指定缓存容量
            mDiskLruCache = DiskLruCache.open(directory, 1, 1, 50 * MB);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getDiskCache(String mCacheDir) {
        return new File(Environment.getExternalStorageDirectory(), mCacheDir);
    }

    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        DiskLruCache.Editor edtor = null;
        OutputStream os = null;
        try {
            //路径必须是合法字符
            edtor = mDiskLruCache.edit(request.getImageUriMD5());
            os = edtor.newOutputStream(0);
            if (persistBitmap2Disk(bitmap, os)) {
                edtor.commit();
            } else {
                edtor.abort();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean persistBitmap2Disk(Bitmap bitmap, OutputStream os) {
        BufferedOutputStream bos = new BufferedOutputStream(os);

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        try {
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;

    }

    @Override
    public Bitmap get(BitmapRequest request) {
        if (mDiskLruCache != null) {
            try {
                DiskLruCache.Snapshot snapshot = mDiskLruCache.get(request.getImageUriMD5());
                if (snapshot != null) {
                    InputStream inputStream = snapshot.getInputStream(0);
                    return BitmapFactory.decodeStream(inputStream);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void remove(BitmapRequest request) {
        try {
            mDiskLruCache.remove(request.getImageUriMD5());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
