package com.xyl.app.image;

import android.widget.ImageView;

import com.xyl.app.image.request.BitmapRequest;
import com.xyl.app.image.request.RequestQueue;

/**
 * 1、拿到URI和imageView对象（getView）生产一个请求（BitmapRequest）
 * 2、将这些请求放到一个队列(PriorityBlockingQueue)里面去
 * 3、转发器（RequestDispatcher）负责去消费这些请求，将这些请求通过加载器（Loader）去加载
 * 4、设置配置信息，如加载策略、缓存策略、开启的线程数等
 * ---------------------
 *
 * @author xyl on 2019/4/4.
 */
public class SimpleImageLoader {
    private ImageLoaderConfig config;
    private RequestQueue requestQueue;
    private static volatile SimpleImageLoader instance;

    private SimpleImageLoader() {

    }

    private SimpleImageLoader(ImageLoaderConfig config) {
        this.config = config;
        requestQueue = new RequestQueue(config.getThreadCount());
        requestQueue.start();
    }

    public static SimpleImageLoader getInstance(ImageLoaderConfig config) {
        if (instance == null) {
            synchronized (SimpleImageLoader.class) {
                if (instance == null) {
                    instance = new SimpleImageLoader(config);
                }
            }
        }
        return instance;
    }

    /**
     * 第二次获取单例
     *
     * @return
     */
    public static SimpleImageLoader getInstance() {
        if (instance == null) {
            throw new UnsupportedOperationException("没有初始化");
        }
        return instance;
    }

    public void displayImage(ImageView imageView, String uri) {
        displayImage(imageView, uri, null, null);
    }

    /**
     * 重载
     *
     * @param imageView
     * @param uri
     * @param displayConfig
     * @param imageListener
     */
    public void displayImage(ImageView imageView, String uri
            , DisplayConfig displayConfig, ImageListener imageListener) {
        //实例化一个请求
        BitmapRequest bitmapRequest = new BitmapRequest(imageView, uri, displayConfig, imageListener);
        //添加到队列里面
        requestQueue.addRequest(bitmapRequest);
    }

    public ImageLoaderConfig getConfig() {
        return config;
    }
}
