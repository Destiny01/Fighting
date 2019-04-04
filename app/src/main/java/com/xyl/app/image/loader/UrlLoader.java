package com.xyl.app.image.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.xyl.app.image.util.ImageViewHelper;
import com.xyl.app.image.request.BitmapRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 网络加载图片
 *
 * @author xyl on 2019/4/4.
 */
public class UrlLoader extends AbstarctLoader {
    @Override
    protected Bitmap onLoad(final BitmapRequest request) {
        //先下载 后读取
        downloadImageByUrl(request.getImageUrl(), getCache(request.getImageUriMD5()));
        BitmapDecoder decoder = new BitmapDecoder() {
            @Override
            public Bitmap decodeBitmapWithOption(BitmapFactory.Options options) {
                return BitmapFactory.decodeFile(getCache(request.getImageUriMD5()).getAbsolutePath(), options);
            }
        };
        return decoder.decodeBitmap(ImageViewHelper.getImageViewWidth(request.getImageView())
                , ImageViewHelper.getImageViewHeight(request.getImageView()));
    }

    public static boolean downloadImageByUrl(String urlStr, File file) {
        FileOutputStream fos = null;
        InputStream is = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            is = httpURLConnection.getInputStream();
            fos = new FileOutputStream(file);
            byte[] bytes = new byte[512];
            int len = 0;
            while ((len = is.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
            fos.flush();
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 获取缓存的文件
     *
     * @param child
     * @return
     */
    private File getCache(String child) {
        File dir = new File(Environment.getExternalStorageDirectory(), "Image");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(dir, child);
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return file;
    }
}
