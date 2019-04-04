package com.xyl.app.image.request;

import android.util.Log;
import android.widget.ImageView;

import com.xyl.app.image.DisplayConfig;
import com.xyl.app.image.ImageListener;
import com.xyl.app.image.util.MD5Utils;
import com.xyl.app.image.SimpleImageLoader;
import com.xyl.app.image.loadPolicy.LoadPolicy;

import java.lang.ref.SoftReference;

import androidx.annotation.Nullable;

/**
 * @author xyl on 2019/4/4.
 */
public class BitmapRequest implements Comparable<BitmapRequest> {
    //持有imageView的软引用
    private SoftReference<ImageView> imageViewSoftReference;
    //图片路径
    private String imageUrl;
    //MD5的图片路径
    private String imageUriMD5;
    //图片编号
    private int serialNo;
    //下载完成监听
    public ImageListener imageListener;
    private DisplayConfig displayConfig;
    //加载策略
    private LoadPolicy loadPolicy = SimpleImageLoader.getInstance().getConfig().getLoadPolicy();

    public BitmapRequest(ImageView imageView, String imageUrl, DisplayConfig displayConfig, ImageListener imageListener) {
        this.imageViewSoftReference = new SoftReference<>(imageView);
        //设置可见的Image的tag
        imageView.setTag(imageUrl);
        this.imageUrl = imageUrl;
        this.imageUriMD5 = MD5Utils.toMD5(imageUrl);
        Log.d("BitmapRequest", "md5 = " + imageUriMD5);
        if (displayConfig != null) {
            this.displayConfig = displayConfig;
        }
        this.imageListener = imageListener;
    }


    public String getImageUrl() {
        return imageUrl;
    }


    public String getImageUriMD5() {
        return imageUriMD5;
    }


    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public ImageView getImageView() {
        return imageViewSoftReference.get();
    }

//    public DisplayConfig getDisplayConfig() {
//        return displayConfig;
//    }
//
//    public LoadPolicy getLoadPolicy() {
//        return loadPolicy;
//    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitmapRequest that = (BitmapRequest) o;

        if (serialNo != that.serialNo) return false;
        return loadPolicy != null ? loadPolicy.equals(that.loadPolicy) : that.loadPolicy == null;
    }

    @Override
    public int hashCode() {
        int result = loadPolicy != null ? loadPolicy.hashCode() : 0;
        result = 31 * result + serialNo;
        return result;
    }

    @Override
    public int compareTo(BitmapRequest o) {
        return loadPolicy.compareTo(o, this);
    }

}
